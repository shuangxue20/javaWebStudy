// 模拟宝藏地图API
class TreasureMap {  
    static getInitialClue() {  
        return new Promise((resolve) => {  
            setTimeout(() => { 
                resolve("在古老的图书馆里找到了第一个线索...");  
            }, 1000);  
        });  
    }  

    static decodeAncientScript(clue) {  
        return new Promise((resolve, reject) => {  
            setTimeout(() => {  
                if (!clue) {  
                    reject("没有线索可以解码!");  
                }  
                resolve("解码成功!宝藏在一座古老的神庙中...");  
            }, 1500);  
        });  
    }  

    static searchTemple(location) {  
        return new Promise((resolve, reject) => {  
            setTimeout(() => {  
                const random = Math.random();  
                if (random < 0.5) {  
                    reject("糟糕!遇到了神庙守卫!");  
                }  
                resolve("找到了一个神秘的箱子...");  
            }, 2000);  
        });  
    }  

    static solvePuzzle() {  
        return new Promise((resolve, reject) => {  
            setTimeout(() => {  
                const random = Math.random();  
                if (random < 0.5) {  
                    reject("谜题解答失败!");  
                }  
                resolve("谜题解答成功，宝箱可以打开!");  
            }, 1500);  
        });  
    }  

    static openTreasureBox() {  
        return new Promise((resolve) => {  
            setTimeout(() => {  
                resolve("恭喜!你找到了传说中的宝藏!");  
            }, 1000);  
        });  
    }  
    static ground() {  
        return new Promise((resolve) => {       
        resolve("图书馆: 古老的图书馆里书架上满是灰尘，藏有许多珍贵的书籍。"
            +"神庙: 这座神庙隐藏在森林深处，崇拜着古老的神祇。"
            +"守卫: 神庙外有一个高大的守卫，他面无表情，手握长矛，不让任何人靠近。");   
        });  
    }  
}  
  




async function findTreasureWithAsyncAwait() {  
    const outputDiv = document.getElementById('output');  
    
    try {  
        //const ground = await TreasureMap.ground();  
        //displayMessage(ground,false);
        
        const clue = await TreasureMap.getInitialClue();  
        displayMessage(clue, true);  
        
        const location = await TreasureMap.decodeAncientScript(clue);  
        displayMessage(location, true);  
        
        const box = await TreasureMap.searchTemple(location);  
        displayMessage(box, true);  
        
        const puzzle = await TreasureMap.solvePuzzle();  
        displayMessage(puzzle, true);  
        
        const treasure = await TreasureMap.openTreasureBox();  
        displayMessage(treasure, true);  
    } catch (error) {  
        displayMessage("任务失败: " + error, false);  
        playSound('failure-sound');  
    }  
}  

function displayMessage(message, success) {  
    const outputDiv = document.getElementById('output');  
    const messageDiv = document.createElement('div');  
    messageDiv.textContent = message;  
    messageDiv.classList.add('fade');  
    if (success) {  
        messageDiv.classList.add('success');   
        playSound('success-sound');  
    } else {  
        messageDiv.classList.add('failure');   
    }  
    outputDiv.appendChild(messageDiv);  
    setTimeout(() => {  
        messageDiv.classList.add('visible');  
    }, 10);  
}  

function playSound(soundId) {  
    const sound = document.getElementById(soundId);  
    sound.currentTime = 0; // 重置声音  
    sound.play();
}  



//加载txt
async function loadGameData() {  
    try {  
        const response = await fetch('game_data.txt');  
        const data = await response.text();  
        const gameData = data.split('\n').reduce((acc, line) => {  
            const [key, value] = line.split(':');  
            if (key && value) acc[key.trim()] = value.trim();  
            return acc;  
        }, {});  
        displayGameData(gameData);  
    } catch (error) {  
        console.error('加载游戏数据失败:', error);  
    }  
}  

function displayGameData(gameData) {  
    const outputDiv = document.getElementById('output');  
    for (const [key, value] of Object.entries(gameData)) {  
        const messageDiv = document.createElement('div');  
        messageDiv.textContent = `${key}: ${value}`;  
        outputDiv.appendChild(messageDiv);  
    }  
}


//保存玩家信息
function savePlayerData(playerId, playerName, gameHistory) {  
    const playerData = {  
        playerId,  
        playerName,  
        gameHistory,  
    };  
    localStorage.setItem('playerData', JSON.stringify(playerData));  
}  

function loadPlayerData() {  
    const playerData = localStorage.getItem('playerData');  
    if (playerData) {  
        return JSON.parse(playerData);  
    }  
    return null;  
}  





// 开始背景音乐  
function playBackgroundMusic() {  
    const music = document.getElementById('background-music');  
    music.play();  
}  


// 在游戏开始时加载玩家数据  
window.onload = () => {  
    const playerData = loadPlayerData();  
    if (playerData) {  
        // 恢复玩家数据（比如昵称和历史）逻辑...  
        displayMessage(`欢迎回来，${playerData.playerName}`, true);  
    } else {  
        // 如果不存在玩家数据，要求输入ID和昵称  
        const playerId = prompt("请输入玩家ID:");  
        const playerName = prompt("请输入玩家昵称:");  
        savePlayerData(playerId, playerName, []);  
    }  

    playBackgroundMusic(); // 播放背景音乐  
    loadGameData(); // 加载游戏数据  
    findTreasureWithAsyncAwait();  
}

//findTreasureWithAsyncAwait();