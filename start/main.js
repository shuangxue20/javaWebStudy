const aliceTumbling = [
  { transform: 'rotate(0) scale(1)' },
  { transform: 'rotate(360deg) scale(0)' }
];

const aliceTiming = {
  duration: 2000,
  iterations: 1,
  fill: 'forwards'
}

const alice1 = document.querySelector("#alice1");
const alice2 = document.querySelector("#alice2");
const alice3 = document.querySelector("#alice3");

function animateElement(element) {  
  return element.animate(aliceTumbling, aliceTiming).finished;  
}  

// 使用 Promise 链依次启动动画  
animateElement(alice1)  
  .then(() => animateElement(alice2))  
  .then(() => animateElement(alice3))  
  .catch((error) => {  
    console.error('动画错误::', error);  
  });  
