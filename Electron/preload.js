// All of the Node.js APIs are available in the preload process.
// It has the same sandbox as a Chrome extension.

const { ipcRenderer } = require('electron');

window.addEventListener('DOMContentLoaded', () => {
  const replaceText = (selector, text) => {
    const element = document.getElementById(selector)
    if (element) element.innerText = text
  }

document.getElementById('flogin').addEventListener('submit', (evt) => {
const input = evt.target[0]
const input1 = evt.target[1]

const login = {
email: input.value,
password: input1.value
};


ipcRenderer.send('submitForm', login);


})






  for (const type of ['chrome', 'node', 'electron']) {
    replaceText(`${type}-version`, process.versions[type])

  }
})



