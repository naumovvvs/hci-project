var element = window.location.pathname.split("/")[2];
var el = document.getElementById("pageTitle");
el.innerText = element.charAt(0).toUpperCase() + element.slice(1) + " Recipes";