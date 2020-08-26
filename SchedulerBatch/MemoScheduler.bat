@echo off

rem 스케줄러 디렉토리 설정
set SchedulerDir=C:\Users\kshma\Documents\SchedulerBatch

rem Telegram 변수
set chat_id=-381510796
set auth_key=1334891991:AAGkrXOd3oPP92tQzRclNY4nmxwuvMPIUqM
set botURL=https://api.telegram.org/bot

rem Mysql MemoList 조회
mysql --host=localhost --user=root --password=root --database=webservice < %SchedulerDir%/script.sql > %SchedulerDir%/MemoList_1.txt
more +1 %SchedulerDir%"/MemoList_1.txt" > %SchedulerDir%"/MemoList.txt"

rem 등록된 값 없을 시 예외처리
for /f %%i in ("%SchedulerDir%/MemoList.txt") do set size=%%~zi
if %size% == 0 start chrome.exe %botURL%%auth_key%/sendmessage?chat_id=%chat_id%^&"text=No Registered Memo."
	
echo %chat_id%
echo %auth_key%
echo %botURL%

rem 텔레그램 API SendMessage 호출 후 종료
for /F "tokens=*" %%A in (%SchedulerDir%/MemoList.txt) do (
	rem echo %botURL%%auth_key%/sendmessage?chat_id=%chat_id%^&text=%%A"
	start chrome.exe %botURL%%auth_key%/sendmessage?chat_id=%chat_id%^&"text=%%A"
	timeout 1
	rem taskkill /F /IM chrome.exe
	
	echo message Send Log : %date% %time% >> MessageSend.log
)

del /q "MemoList_1.txt"

rem pause