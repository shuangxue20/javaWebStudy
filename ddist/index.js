// import { formatDistanceToNow } from "date-fns";

// const date = "1996-09-13 10:00:00";
// document.body.textContent = `${formatDistanceToNow(new Date(date))} ago`;
// 定义一个函数，当按钮被点击时执行
function changeBackgroundColor() {
  // 生成随机颜色
  const randomColor = '#' + Math.floor(Math.random() * 16777215).toString(16);
  // 设置body的背景颜色
  document.body.style.backgroundColor = randomColor;
}

// 使用document.getElementById获取按钮元素
const changeColorButton = document.getElementById('changeColorButton');

// 为按钮添加点击事件监听器
changeColorButton.addEventListener('click', changeBackgroundColor);