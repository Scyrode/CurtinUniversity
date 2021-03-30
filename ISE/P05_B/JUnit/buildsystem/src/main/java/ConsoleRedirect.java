import java.io.*;
import java.util.*;

/**
 * <p>A collection of utility methods to manipulate the console for testing purposes. This class
 * can be used to help test any code that reads from standard input and/or writes to standard 
 * output.</p>
 *
 * <p>This includes the use of Scanner or BufferedReader, for instance, in conjunction with 
 * System.in. However, it <em>does not</em> include java.io.Console, which uses a separate 
 * mechanism to perform input and output.</p>
 *
 * <p>WARNING: this code contains advanced constructs not taught in OOPD or ISE. It is intended
 * to be compiled and called in your ISE test code (for the test fixtures practical work), but the 
 * code here in this file is NOT something you need to study or modify.</p>
 *
 * @author David Cooper
 */
public final class ConsoleRedirect
{
    /**
     * <p>Wraps another input stream, similar to FilterInputStream, but with the explicit ability 
     * to swap out the underlying stream for a different one.</p>
     */
    private static class ProxyInputStream extends InputStream
    {
        private InputStream original;
        private InputStream is;

        public ProxyInputStream(InputStream original)
        {
            this.original = original;
            this.is = original;
        }

        @Override
        public int available() throws IOException
        {
            return is.available();
        }

        @Override
        public void close() throws IOException
        {
            is.close();
        }

        @Override
        public int read() throws IOException
        {
            return is.read();
        }

        public void set(InputStream is)
        {
            this.is = is;
        }
        
        public InputStream get()
        {
            return this.is;
        }

        public void restoreOriginal()
        {
            this.is = original;
        }
    }
    
    /**
     * <p>Wraps another output stream, similar to FilterOutputStream, but with the explicit ability 
     * to swap out the underlying stream for a different one.</p>
     */
    private static class ProxyOutputStream extends OutputStream
    {
        private OutputStream original;
        private OutputStream os;

        public ProxyOutputStream(OutputStream os)
        {
            this.original = os;
            this.os = os;
        }

        @Override
        public void write(int i) throws IOException
        {
            os.write(i);
        }

        @Override
        public void flush() throws IOException
        {
            os.flush();
        }

        @Override
        public void close() throws IOException
        {
            os.close();
        }

        public void set(OutputStream os)
        {
            this.os = os;
        }
        
        public OutputStream get()
        {
            return this.os;
        }

        public void restoreOriginal()
        {
            this.os = this.original;
        }
    }
    
    /**
     * <p>Thrown when the production code attempts to read past the end of the fake input supplied 
     * by the test code.</p>
     */
    public static class FakeInputError extends Error
    {
        public FakeInputError(String msg)
        {
            super(msg);
        }
    }

    /**
     * <p>Similar to ByteArrayInputStream. This class creates an input stream from the byte 
     * contents of Strings. It differs from ByteArrayInputStream in three ways: (1) its buffer is 
     * extendable, (2) it focuses on Strings specifically, and (3) the read() method has slightly 
     * different semantics when the end-of-stream is reached.</p>
     */
    private static class MockInputStream extends InputStream
    {
        private char[] data;
        private int i;

        MockInputStream(String s)
        {
            data = s.toCharArray();
            i = 0;
        }

        /**
        * <p>"Reads" the next available byte from the String data. The contract for this method 
        * requires it to return -1 to signify the end of the stream. java.util.Scanner in 
        * particular requires this, or it won't stop reading. So we return -1 once, when all the 
        * bytes are exhausted.</p>
        *
        * <p>However, any further read attempts will trigger FakeInputError, as a means to flag
        * attempts to read "too much" input, which (for testing purposes) implies that the test 
        * should fail.</p>
        *
        * @return The next byte in the sequence, or -1 (once) if all bytes have been read.
        *
        * @throws FakeInputError On the call *after* the one where -1 is returned to signify
        *                        end-of-stream.
        */
        @Override
        public int read() throws FakeInputError
        {
            int r;
            if(i < data.length)
            {
                r = (int) data[i];
            }
            else if(i == data.length)
            {
                r = -1;
            }
            else
            {
                throw new FakeInputError(
                    "Insufficient fake input. The production code is trying to read more input "
                    + "than the test code is providing.\"");
            }
            i++;
            return r;
        }

        /**
        * <p>Concatenates more String data onto the existing data to be read. This would normally 
        * be done either <em>before</em> any reading takes place, or <em>after</em> everything has 
        * been read. In the latter case, the new data just replaces the existing, already-read 
        * data. (This method can theoretically be called part-way through data being read, but this
        * case is not subject to extensive testing.)</p>
        *
        * @param s The new data to be concatenated.
        */
        void add(String s)
        {
            if(i < data.length)
            {
                int origLength = data.length;
                char[] newData = s.toCharArray();
                data = Arrays.copyOf(data, origLength + newData.length);
                System.arraycopy(newData, 0, data, origLength, newData.length);
            }
            else
            {
                data = s.toCharArray();
                i = 0;
            }
        }
    }

    private static MockInputStream inputBuffer = null;
    private static ByteArrayOutputStream outputBuffer = null;
    private static final ProxyInputStream in;
    private static final ProxyOutputStream out;

    static
    {
        in = new ProxyInputStream(System.in);
        out = new ProxyOutputStream(System.out);

        System.setIn(in);
        System.setOut(new PrintStream(out));
    }

    /**
     * <p>Injects fake console input, which will subsequently be "read in" by any console-reading 
     * code. The fake input is provided line-by-line, emulating the console's line-based input 
     * buffer. If called multiple times, each successive fake input string is appended to the 
     * input buffer, preceded by a line break.</p>
     *
     * <p>This method effectively disables real console input, until restoreInput() is called.</p>
     */
    public static void fakeInput(String input)
    {
        if(inputBuffer == null)
        {
            inputBuffer = new MockInputStream(input + '\n');
            in.set(inputBuffer);
        }
        else
        {
            inputBuffer.add(input + '\n');
        }
    }

    /**
     * <p>Begins capturing console output. Once this method is called, all subsequent console 
     * output is redirected to a buffer, which can be read using getCapturedOutput().</p>
     *
     * <p>This method effectively prevents standard output being displayed on the console, until
     * restoreOutput() is called. However, standard error (e.g. System.err.println()) can still be
     * used.</p>
     */
    public static void captureOutput()
    {
        if(outputBuffer == null)
        {
            outputBuffer = new ByteArrayOutputStream();
            out.set(outputBuffer);
        }
    }

    /**
     * <p>Retrieves all console output captured since captureOutput() was called, or
     * getCapturedOutput() was last called, whichever was more recent. The output is returned as a
     * string, with no special treatment of new lines or any other characters.</p>
     *
     * <p>This method also resets the output buffer, so subsequent calls will not return the same
     * output.</p>
     */
    public static String getCapturedOutput()
    {
        if(outputBuffer == null)
        {
            throw new NullPointerException(
                "Did you forget to call captureOutput() before getCapturedOutput()?");
        }
        String output = outputBuffer.toString();
        outputBuffer.reset();
        return output.trim();
    }

    /**
     * <p>Restores standard input to the console, after fake input has been used. Subsequent attempts
     * to read from the console will read from the actual console, and not from the fake input
     * buffer.</p>
     */
    public static void restoreInput()
    {
        in.restoreOriginal();
        inputBuffer = null;
    }

    /**
     * Restores standard output to the console, after output has been captured. Subsequent attempts
     * to output to the console will *really* output to the console, and not to the captured output
     * buffer.
     */
    public static void restoreOutput()
    {
        out.restoreOriginal();
        outputBuffer = null;
    }
}
