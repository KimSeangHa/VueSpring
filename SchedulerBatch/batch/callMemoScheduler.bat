@echo off

set SchedulerDir=C:\Users\kshma\Documents\SchedulerBatch

for /f %%i in ("%SchedulerDir%/MemoList.txt") do set size=%%~zi
return

rem DB 리스트 갱신
call %SchedulerDir%/batch/MemoDbList.bat

rem DB 라인수 만큼 함수호출
for /F "tokens=*" %%A in (%SchedulerDir%/MemoList.txt) do (
	call %SchedulerDir%/batch/MemoScheduler22.bat
)
	