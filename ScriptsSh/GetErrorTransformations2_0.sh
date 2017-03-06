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
  echo "anio = $year"
  echo "mes = $month"
  echo "dia = $day"
  exit 1
 fi
}

processArgs $*
echo "Iniciando proceso de obtencion de errores"
cd /tmp/Logs/Operations/IC/Transformations/$year/$month/$day
prio="TRANSFORMATION_IC_*"
comGrep="  | egrep -B 30 'FAILED' > "
archivo="Error"
separador="_"
todos="*"
extencion=".txt"
DIA=`date +"%Y_%m_%d"`
HORA=`date +"%H_%M"`
if [ -z "$countries" ] ;
then shift ;
grep -H -B 30 "FAILED" TRANSFORMATION_IC_* > /tmp/Logs/Operations/IC/Transformations/ErroresTotal$separador$DIA$separador$HORA.txt
else
echo "Mas de uno"
IFS=',' read -ra ADDR <<< "$countries"
for i in "${ADDR[@]}"; do
    countri=$(echo $i | cut -c1-3)
    archivo="$archivo$separador$countri"
    cad=$cad" "$prio$i$todos
done
archivo=$archivo$separador$DIA$separador$HORA$extencion
grep -H -B 30 "FAILED" $cad > $archivo > /tmp/Logs/Operations/IC/Transformations/$archivo
fi
echo "Proceso obtencion de errores Finalizado"
