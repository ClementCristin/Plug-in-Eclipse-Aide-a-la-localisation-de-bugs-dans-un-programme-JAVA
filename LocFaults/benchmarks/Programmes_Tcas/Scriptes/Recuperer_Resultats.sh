chemin_Programmes_Tcas=/home/mdbekkouche/These/Benchmarks_MCS-IIS/Programs_Benchmarks_MCS-IIS/Programmes_Tcas

w="";
while read line
do
  if [[ ${line:0:13} =  "The execution" ]]
  then 
    w="$(echo $line | cut -d":" -f2)";
    echo $w;  
  fi  
done < $chemin_Programmes_Tcas/TcasKO/TcasKO_Faults/Faults_TcasKO_t1.java.faults

