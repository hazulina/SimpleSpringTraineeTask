@echo off

REM Пересборка проекта с использованием Maven
call mvn clean package

REM Ожидание завершения процесса mvn с использованием PowerShell
powershell -Command "while (Get-Process -Name mvn -ErrorAction SilentlyContinue) { Start-Sleep -Seconds 1 }"

REM Остановка Docker Compose, если он уже запущен
docker-compose down

REM Запуск Docker Compose
docker-compose up -d