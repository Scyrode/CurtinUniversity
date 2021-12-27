document.addEventListener("DOMContentLoaded", function(event) { 
    const REMOVE_BTNS = document.querySelectorAll(".remove-button")
    
    function removePane (e) {
        e.currentTarget.parentElement.remove()
    }
    
    for (const REMOVE_BTN in REMOVE_BTNS) {
        REMOVE_BTN.addEventListener('click', removePane)
    }
});