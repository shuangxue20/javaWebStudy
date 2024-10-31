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
}  
 



async function findTreasureWithAsyncAwait() {  
    const outputDiv = document.getElementById('output');  
    
    try {  
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

findTreasureWithAsyncAwait();