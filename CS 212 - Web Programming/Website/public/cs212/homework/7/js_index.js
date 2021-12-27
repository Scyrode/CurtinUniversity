const FIRST_NUM_BOX = document.querySelector("#firstNum-box")
const SECOND_NUM_BOX = document.querySelector("#secondNum-box")
const THIRD_NUM_BOX = document.querySelector("#thirdNum-box")
const FOURTH_NUM_BOX = document.querySelector("#fourthNum-box")
const FIFTH_NUM_BOX = document.querySelector("#fifthNum-box")

const ANSWER_DISPLAY = document.querySelector("#answer-display")

const START_CALCULATION_BTN = document.querySelector("#startButton")

console.log("test");

let min
let max
let average

function startCalculation () {
    const firstNum = parseInt(FIRST_NUM_BOX.value)
    const secondNum = parseInt(SECOND_NUM_BOX.value)
    const thirdNum = parseInt(THIRD_NUM_BOX.value)
    const fourthNum = parseInt(FOURTH_NUM_BOX.value)
    const fifthNum = parseInt(FIFTH_NUM_BOX.value)

    min = Math.min(firstNum, secondNum, thirdNum, fourthNum, fifthNum)
    max = Math.max(firstNum, secondNum, thirdNum, fourthNum, fifthNum)
    average = (firstNum + secondNum + thirdNum + fourthNum + fifthNum) / 5

    ANSWER_DISPLAY.innerText = `min: ${min}    max: ${max}    average:${average}`
}

START_CALCULATION_BTN.addEventListener('click', startCalculation)