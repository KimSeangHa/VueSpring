@echo off

rem 스케줄러 디렉토리 설정
set SchedulerDir=C:\Users\kshma\Documents\SchedulerBatch

rem Mysql MemoList 조회
mysql --host=localhost --user=root --password=root --database=webservice < %SchedulerDir%/sql/script.sql > %SchedulerDir%/MemoList_1.txt
more +1 %SchedulerDir%"/MemoList_1.txt" > %SchedulerDir%"/MemoList.txt"

mysql --host=localhost --user=root --password=root --database=webservice < %SchedulerDir%/sql/chatscript.sql > %SchedulerDir%/ChatList_1.txt
more +1 %SchedulerDir%"/ChatList_1.txt" > %SchedulerDir%"/ChatList.txt"


rem count 초기화
echo 0 > %SchedulerDir%/cnt/count.txt