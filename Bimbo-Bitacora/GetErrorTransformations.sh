#!/bin/bash
#############################################
# Function to process arguments     #
#############################################
processArgs() {
 usage="--anio name --mes name --dia name --paises name"
 while (( "$#" )); do
  if [ $1 = "--anio" ] ; then
   shift ; anio=$1 ; shift ;
  elif [ $1 = "--mes" ] ; then
   shift ; mes=$1 ; shift ;
  elif [ $1 = "--dia" ] ; then
   shift ; dia=$1 ; shift ;
  elif [ $1 = "--paises" ] ; then
   shift ; paises=$1 ; shift ;
  else
   echo "invalid argument: :" $1
   echo "$0: $usage"
   exit 1
  fi
 done
 if [ -z $anio ] || [ -z $mes ] || [ -z $dia ] ; then
  echo "$0 : bad/insufficent arguments"
  echo "$0 : $usage"
  echo "anio = $anio"
  echo "mes = $mes"
  echo "dia = $dia"
  exit 1
 fi
}

processArgs $*
echo "Iniciando proceso de obtencion de errores"
cd /$HOME/IC/Logs/Transformations/$anio/$mes/$dia
prio="TRANSFORMATION_IC_*"
comGrep="  | egrep -B 30 'FAILED' > "
archivo="Error"
separador="_"
todos="*"
extencion=".txt"
DIA=`date +"%Y_%m_%d"`
HORA=`date +"%H_%M"`
if [ -z "$paises" ] ;
then shift ;
grep -H -B 30 "FAILED" TRANSFORMATION_IC_* > /tmp/Logs/Operations/IC/Transformations/ErroresTotal$separador$DIA$separador$HORA.txt
else
echo "Mas de uno"
IFS=',' read -ra ADDR <<< "$paises"
for i in "${ADDR[@]}"; do
    pais=$(echo $i | cut -c1-3)
    archivo="$archivo$separador$pais"
    cad=$cad" "$prio$i$todos
done
archivo=$archivo$separador$DIA$separador$HORA$extencion
grep -H -B 30 "FAILED" $cad > $archivo > /tmp/Logs/Operations/IC/Transformations/$archivo
fi
echo "Proceso obtencion de errores Finalizado"

