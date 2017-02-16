set -x
cd /home/work/yujie/showstartData || exit 101
DATE=`date -d -1day +%Y%m%d`
#天级运行user
bflag=0
i=0
while  [ $bflag -ne 1 -a $i -lt 5 ]
do
rm -rf showstart_user_$DATE
java -Dfile.encoding=utf-8 -jar /home/work/yujie/showstartData/userByday.jar $DATE
if [[ $? -eq 0 ]];then
        bflag=1
        #break
fi
i=`expr $i + 1`
sleep 1
done
#artist
bflag=0
i=0
while  [ $bflag -ne 1 -a $i -lt 5 ]
do
rm -rf showstart_artist_$DATE
java -Dfile.encoding=utf-8 -jar /home/work/yujie/showstartData/artistByday.jar $DATE
if [[ $? -eq 0 ]];then
        bflag=1
        #break
fi
i=`expr $i + 1`
sleep 1
done
#showlist
bflag=0
i=0
while [ $bflag -ne 1 -a $i -lt 5 ]
do
rm -rf showstart_showlist_$DATE
java -Dfile.encoding=utf-8 -jar /home/work/yujie/showstartData/showlistByday.jar $DATE
if [[ $? -eq 0 ]] ;then
        bflag=1
        #break
fi
i=`expr $i + 1`
sleep 1
done
#order
bflag=0
i=0
while  [ $bflag -ne 1 -a $i -lt 5 ]
do
rm -rf showstart_order_$DATE
java -Dfile.encoding=utf-8 -jar /home/work/yujie/showstartData/orderByday.jar $DATE
if [[ $? -eq 0 ]] ;then
        bflag=1
        #break  
fi
i=`expr $i + 1`
sleep 1
done
hadoop fs -test -e /music/session/showstart_artist_$DATE
if [ $? -eq 0 ] ;then
	hadoop fs -rm  /music/session/showstart_artist_$DATE
fi
sleep 5

hadoop fs -put showstart_artist_$DATE /music/session/
while [ $? -ne 0 ]
do
        hadoop fs -put showstart_artist_$DATE /music/session/
        hadoop fs -test -e /music/session/showstart_artist_$DATE
        if [ $? -eq 0 ] ;then
                break
        fi
done

hadoop fs -test -e /music/session/showstart_order_$DATE
if [ $? -eq 0 ] ;then
        hadoop fs -rm  /music/session/showstart_order_$DATE
fi
sleep 5

hadoop fs -put showstart_order_$DATE /music/session/
while [ $? -ne 0 ]
do
        hadoop fs -put showstart_order_$DATE /music/session/
        hadoop fs -test -e /music/session/showstart_order_$DATE
        if [ $? -eq 0 ] ;then
                break
        fi
done



hadoop fs -test -e /music/session/showstart_user_$DATE
if [ $? -eq 0 ] ;then
        hadoop fs -rm  /music/session/showstart_user_$DATE
fi
sleep 5
hadoop fs -put showstart_user_$DATE /music/session/
while [ $? -ne 0 ]
do
        hadoop fs -put showstart_user_$DATE /music/session/
        hadoop fs -test -e /music/session/showstart_user_$DATE
        if [ $? -eq 0 ] ;then
                break
        fi
done



hadoop fs -test -e /music/session/showstart_showlist_$DATE
if [ $? -eq 0 ] ;then
        hadoop fs -rm  /music/session/showstart_showlist_$DATE
fi
sleep 5
hadoop fs -put showstart_showlist_$DATE /music/session/
while [ $? -ne 0 ]
do
        hadoop fs -put showstart_showlist_$DATE /music/session/
        hadoop fs -test -e /music/session/showstart_showlist_$DATE
        if [ $? -eq 0 ] ;then
                break
        fi
done

#hadoop fs -put showstart_fans_$DATE /music/session/

#删除四天前的文件
DATE1=`date -d -4days +%Y%m%d`
rm -rf showstart_artist_$DATE1
if [[ $? -eq 0 ]] ;then 
	echo 'delete artist success';
else 
	echo 'delete artist fail';
fi
rm -rf showstart_order_$DATE1
if [[ $? -eq 0 ]] ;then
        echo 'delete order success';
else
        echo 'delete order fail';
fi
rm -rf showstart_user_$DATE1
if [[ $? -eq 0 ]] ;then
        echo 'delete user success';
else
        echo 'delete user fail';
fi
rm -rf showstart_showlist_$DATE1
if [[ $? -eq 0 ]] ;then
        echo 'delete showlist success';
else
        echo 'delete showlist fail';
fi
echo $?
