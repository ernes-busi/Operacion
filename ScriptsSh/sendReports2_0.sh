#!/bin/bash
source $HOME/IC/Notifications/recipients2_0.properties

processArgs() {
usage="--country name --organization name --dateE name --startE name --endE name --timeE name --errorsE name --dateT name --startT name --endT name --timeT name --errorsT name"
 while (( "$#" )); do
 if [ $1 = "--country" ] ; then
        shift ; country=$1 ; shift ;

    elif [ $1 = "--organization" ] ; then
        shift ; organization=$1 ; shift ;

    elif [ $1 = "--dateE" ] ; then
        shift ; dateE=$1 ; shift ;

    elif [ $1 = "--startE" ] ; then
        shift ; startE=$1 ; shift ;

    elif [ $1 = "--endE" ] ; then
        shift ; endE=$1 ; shift ;

    elif [ $1 = "--timeE" ] ; then
        shift ; timeE=$1 ; shift ;

    elif [ $1 = "--errorsE" ] ; then
        shift ; errorsE=$1 ; shift ;


    elif [ $1 = "--dateT" ] ; then
        shift ; dateT=$1 ; shift ;

    elif [ $1 = "--startT" ] ; then
        shift ; startT=$1 ; shift ;

    elif [ $1 = "--endT" ] ; then
        shift ; endT=$1 ; shift ;

    elif [ $1 = "--timeT" ] ; then
        shift ; timeT=$1 ; shift ;

    elif [ $1 = "--errorsT" ] ; then
        shift ; errorsT=$1 ; shift ;



else

echo "invalid argument: :" $1

    echo "$0: $usage"

exit 1

   fi

   done
if [ -z $country ] || [ -z $organization ] || [ -z $dateE ] || [ -z $startE ] || [ -z $endE ] || [ -z $timeE ] || [ -z $errorsE ] || [ -z $dateT ] || [ -z $startT ] || [ -z $endT ] || [ -z $timeT ] || [ -z $errorsT ]; then

    echo "$0 : bad/insufficent arguments"

    echo "$0 : $usage"

    echo "country = $country"

    echo "organization = $organization"

    echo "dateE = $dateE"

    echo "startE = $startE"

    echo "endE= $endE"

    echo "timeE= $timeE"

    echo "errorsE = $errorsE"

    echo "dateT = $dateT"

    echo "startT = $startT"

    echo "endT= $endT"

    echo "timeT= $timeT"

    echo "errorsT = $errorsT"


    exit 1

   fi
}

SetOrganization(){
        if [ "$country" = "chile" ] || [ "$country" = "argentina" ] || [ "$country" = "peru" ] || [ "$country" = "uruguay" ] || [ "$country" = "paraguay" ]; then
                organization="LAS"
        elif [ "$country" = "colombia" ] || [ "$country" = "panama" ] || [ "$country" = "honduras" ] || [ "$country" = "nicaragua" ] || [ "$country" = "salvador" ] || [ "$country" = "costarica" ] || [ "$country" = "guatemala" ] || [ "$country" = "ecuador" ] || [ "$country" = "venezuela" ]; then
                organization="LAC"
        elif [ "$country" = "mexico" ]; then
                organization="OB y OBL"
        elif [ "$country" = "china" ]; then
                organization="OA"
        elif [ "$country" = "canada" ]; then
                organization="CAN"
        else
                organization=$country
        fi
        echo "Organization: "$organization
}

GetDate(){
        year=`date -d $dateE '+%Y'`
        month=`date -d $dateE '+%m'`
        day=`date -d $dateE '+%d'`
        echo "valores Fecha: "$year $month $day
}

GetDateT(){
        yearT=`date -d $dateT '+%Y'`
        monthT=`date -d $dateT '+%m'`
        dayT=`date -d $dateT '+%d'`
        echo "valores Fecha: "$yearT $monthT $dayT
}
        SendReport_N1(){

        echo "<!DOCTYPE html><html><head><title>Reporte de carga</title></head><body><p>Hola,</p><p>Se envía la siguiente tabla con el reporte sobre la carga al Data Lake de $country del día $dateT</p><table border="'"2px"'"><caption>Reporte de Carga ($country)</caption><tr><tr><td BGCOLOR="'"A8CAFA"'">Pais</td><td BGCOLOR="'"A8CAFA"'">Proceso</td><td BGCOLOR="'"A8CAFA"'">Status</td></tr><tr><td>$country</td><td>Extraccion</td><td>OK</td></tr><tr><td>$country</td><td>Transformacion</td><td>OK</td></tr></table><p>Saludos</p><h2>OPERACION BIG DATA<h2><h4>Centro de Análisis de Negocio</h4><!-- <p>Alexis Padilla 476 piso 1</br>Nueva Anzures, Mexico, D.F., 11590</br>+52 55 41518078</p><h6>BIG DATA</br>Analytic Data Platforms | Applications | Services </br>soportebigdata@grupobimbo.com </br>www.grupobimbo.com</h6>--></body></html>" | mutt -e 'set content_type="text/html"' $toN1 -c $ccN1 -s "Resumen de carga $organization - $dateT"
        echo "Mensaje nivel 1 enviado"

        }



        SendReport_N2(){

        echo   "<!DOCTYPE html><html><head><title>Reporte de carga</title></head><body><p>Hola,</p><p>Se envía la siguiente tabla con el reporte sobre la carga al Data Lake de $country del día $dateT</p><table border="'"2px"'"><caption>Reporte de Carga ($country)</caption><tr><tr><td BGCOLOR="'"A8CAFA"'">Pais</td><td BGCOLOR="'"A8CAFA"'">Proceso</td><td BGCOLOR="'"A8CAFA"'">Inicio de proceso</td><td BGCOLOR="'"A8CAFA"'">Termino de proceso</td><td BGCOLOR="'"A8CAFA"'">Duración</td><td BGCOLOR="'"A8CAFA"'">Fecha</td><td BGCOLOR="'"A8CAFA"'">Status</td><td BGCOLOR="'"A8CAFA"'">Cantidad de errores</td></tr><tr><td>$country</td><td>Extracción</td><td>$startE</td><td>$endE</td><td>$timeE</td><td>$dateE</td><td>OK</td><td>$errorsE</td></tr><tr><td>$country</td><td>Transformación</td><td>$startT</td><td>$endT</td><td>$timeT</td><td>$dateT</td><td>OK</td><td>$errorsT</td></tr></table><p>Saludos</p><h2>OPERACION BIG DATA<h2><h4>Centro de Análisis de Negocio</h4><!-- <p>Alexis Padilla 476 piso 1</br>Nueva Anzures, Mexico, D.F., 11590</br>+52 55 41518078</p><h6>BIG DATA</br>Analytic Data Platforms | Applications | Services </br>soportebigdata@grupobimbo.com </br>www.grupobimbo.com</h6>--></body></html>" | mutt -e 'set content_type="text/html"' $toN2 -c $ccN2 -s "Resumen de carga $organization - $dateT"
                echo "Mensaje nivel 2 enviado"

        }

        SendReport_N3(){

                 java -jar /home/ec2-user/IC/Notifications/bitacora2_1 $year $month $day $yearT $monthT $dayT $country
                fileErrors="/tmp/Logs/Operations/IC/Reports/Errores_`echo $country`_`date '+%F'`.xls"

                echo   "<!DOCTYPE html><html><head><title>Reporte de carga</title></head><body><p>Hola,</p><p>Se envía la siguiente tabla con el reporte sobre la carga al Data Lake de $country del día $dateT</p><table border="'"2px"'"><caption>Reporte de Carga ($country)</caption><tr><tr><td BGCOLOR="'"A8CAFA"'">Pais</td><td BGCOLOR="'"A8CAFA"'">Proceso</td><td BGCOLOR="'"A8CAFA"'">Inicio de proceso</td><td BGCOLOR="'"A8CAFA"'">Termino de proceso</td><td BGCOLOR="'"A8CAFA"'">Duración</td><td BGCOLOR="'"A8CAFA"'">Fecha</td><td BGCOLOR="'"A8CAFA"'">Status</td><td BGCOLOR="'"A8CAFA"'">Cantidad de errores</td></tr><tr><td>$country</td><td>Extracción</td><td>$startE</td><td>$endE</td><td>$timeE</td><td>$dateE</td><td>OK</td><td>$errorsE</td></tr><tr><td>$country</td><td>Transformación</td><td>$startT</td><td>$endT</td><td>$timeT</td><td>$dateT</td><td>OK</td><td>$errorsT</td></tr></table><p>Saludos</p><h2>OPERACION BIG DATA<h2><h4>Centro de Análisis de Negocio</h4><!-- <p>Alexis Padilla 476 piso 1</br>Nueva Anzures, Mexico, D.F., 11590</br>+52 55 41518078</p><h6>BIG DATA</br>Analytic Data Platforms | Applications | Services </br>soportebigdata@grupobimbo.com </br>www.grupobimbo.com</h6>--></body></html>" | mutt -e 'set content_type="text/html"' $toN3 -c $ccN3 -s "Resumen de carga $organization - $dateT" -a $fileErrors --
                echo "Mensaje nivel 3 enviado"

        }

processArgs $*

echo "########## Inicio de Proceso de Notificaciones ##########"
GetDate
GetDateT
SetOrganization

SendReport_N1

SendReport_N2

SendReport_N3

echo "########## Proceso de notificaciones finalizado ##########"
