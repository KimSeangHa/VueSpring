@echo off

rem 스케줄러 디렉토리 설정
set SchedulerDir=C:\Users\kshma\Documents\SchedulerBatch

rem Telegram 변수
set auth_key=1334891991:AAGkrXOd3oPP92tQzRclNY4nmxwuvMPIUqM
set botURL=https://api.telegram.org/bot


rem 등록된 값 없을 시 예외처리
for /f %%i in ("%SchedulerDir%/MemoList.txt") do set size=%%~zi
return
	
echo %auth_key%
echo %botURL%

set /p count=< %SchedulerDir%%/cnt/count.txt
set /a count=count+1
echo %count% 

more +%count% %SchedulerDir%"/MemoList_1.txt" > %SchedulerDir%"/MemoList.txt"
more +%count% %SchedulerDir%"/ChatList_1.txt" > %SchedulerDir%"/ChatList.txt"

echo %count% > %SchedulerDir%/cnt/count.txt

set /p chat_id=< %SchedulerDir%/ChatList.txt  
set /p content=< %SchedulerDir%/MemoList.txt  
echo %content%
echo %chat%

start chrome.exe %botURL%%auth_key%/sendmessage?chat_id=%chat_id%^&"text=%content%"
timeout 1

echo [ %date% %time% ] :  message Send Result  >> %SchedulerDir%/log/MessageSend.log



