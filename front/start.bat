@echo off
start http-server -p 80
cd /d C:\Program Files\Google\Chrome\Application
start chrome.exe http://localhost/login.html
