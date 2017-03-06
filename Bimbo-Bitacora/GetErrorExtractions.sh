#!/bin/bash
#############################################
# Function to process arguments     #
#############################################
processArgs() {
 usage="--year name --month name --day name --countries name"
 while (( "$#" )); do
  if [ $1 = "--year" ] ; then
   shift ; year=$1 ; shift ;
  elif [ $1 = "--month" ] ; then
   shift ; month=$1 ; shift ;
  elif [ $1 = "--day" ] ; then
   shift ; day=$1 ; shift ;
  elif [ $1 = "--countries" ] ; then
   shift ; countries=$1 ; shift ;
  else
   echo "invalid argument: :" $1
   echo "$0: $usage"
   exit 1
  fi
 done
 if [ -z $year ] || [ -z $month ] || [ -z $day ] ; then
  echo "$0 : bad/insufficent arguments"
  echo "$0 : $usage"
  echo "year = $year"
  echo "month = $month"
  echo "day = $day"
  exit 1
 fi
}

processArgs $*
echo "Iniciando proceso de obtencion de errores"
cd /$HOME/IC/Logs/Extractions/$year/$month/$day
prio="Priority_0_"
comGrep="  | egrep -B300 'SQOOP EXTRACTION|MOVE DATA' > "
archivo="Error"
separador="_"
todos="*"
extencion=".txt"
DIA=`date +"%d_%m_%Y"`
HORA=`date +"%H_%M"`
if [ -z "$countries" ] ;
then shift ;
cat Priority_0_* | egrep -B300 'SQOOP EXTRACTION|MOVE DATA' > /tmp/Logs/operacion/IC/Extraccion/ErroresTotal$separador$DIA$separador$HORA.txt 
else
echo "Mas de uno"
IFS=',' read -ra ADDR <<< "$countries"
for i in "${ADDR[@]}"; do
    pais=$(echo $i | cut -c1-3)
    archivo="$archivo$separador$pais"
    cad=$cad" "$prio$i$todos
done
archivo=$archivo$separador$DIA$separador$HORA$extencion
cat $cad | egrep -B300 'SQOOP EXTRACTION|MOVE DATA' > /tmp/Logs/operacion/IC/Extraccion/$archivo
fi
echo "Proceso obtencion de errores Finalizado"
