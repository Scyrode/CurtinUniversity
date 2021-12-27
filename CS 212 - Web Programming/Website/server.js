const express = require('express')
const logger = require('morgan')
const path = require('path')
const server = express()
server.use(express.urlencoded({'extended': true}))
server.use(logger('dev'))

// Routes
server.get('/do_a_random', (req, res) => {
  res.send(`Your number is: ${Math.floor(Math.random() * 100) + 1}`)
})

server.post('/cs212/homework/8/', (req, res) => {
  response_string = (`Hey, ${req.body['silly-word']}, ${req.body['silly-word']}!<br>` + `The ${req.body.animal} and the ${req.body['musical-instrument']},<br>` + `The cow jumped over the ${req.body.noun1};<br>` + `The ${req.body.adjective} dog laughed<br>` + `To see such sport,<br>` + `And the ${req.body.noun2} ran away with the spoon.`)
  res.send(response_string)
})


// Setup static page serving for all the pages in "public"
const publicServedFilesPath = path.join(__dirname, 'public')
server.use(express.static(publicServedFilesPath))


// The server uses port 80 by default unless you start it with the extra
// command line argument 'local' like this:
//       node server.js local
let port = 80
if (process.argv[2] === 'local') {
  port = 8080
}

server.listen(port, () => console.log('Ready on localhost!'))
