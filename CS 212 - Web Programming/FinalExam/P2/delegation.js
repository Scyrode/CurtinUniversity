const NW = document.querySelector("#table .nw")
const N = document.querySelector("#table .n")
const NE = document.querySelector("#table .ne")
const W = document.querySelector("#table .w")
const C = document.querySelector("#table .c")
const E = document.querySelector("#table .e")
const SW = document.querySelector("#table .sw")
const S = document.querySelector("#table .s")
const SE = document.querySelector("#table .se")

let NW_COLOUR = NW.style.background
let N_COLOUR = N.style.background
let NE_COLOUR = NE.style.background
let W_COLOUR = W.style.background
let C_COLOUR = C.style.background
let E_COLOUR = E.style.background
let SW_COLOUR = SW.style.background
let S_COLOUR = S.style.background
let SE_COLOUR = SE.style.background

function changeColour (e) {
    NW.style.background = NW_COLOUR
    N.style.background = N_COLOUR
    NE.style.background = NE_COLOUR
    W.style.background = W_COLOUR
    C.style.background = C_COLOUR
    E.style.background = E_COLOUR
    SW.style.background = SW_COLOUR
    S.style.background = S_COLOUR
    SE.style.background = SE_COLOUR
    
    e.target.style.background = 'red'
}

NW.addEventListener('click', changeColour)
N.addEventListener('click', changeColour)
NE.addEventListener('click', changeColour)
C.addEventListener('click', changeColour)
W.addEventListener('click', changeColour)
E.addEventListener('click', changeColour)
SW.addEventListener('click', changeColour)
S.addEventListener('click', changeColour)
SE.addEventListener('click', changeColour)