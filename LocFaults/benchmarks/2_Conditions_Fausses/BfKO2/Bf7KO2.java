class bf{

 /*@ requires 
   @ (nodecount==5)
   @ && (Source[0]==0 && Source[1]==1 && Source[2]==3 && Source[3]==2 && Source[4]==4 && Source[5]==2 && Source[6]==0)
   @ && (Dest[0]==1 && Dest[1]==1 && Dest[2]==4 && Dest[3]==1 && Dest[4]==3 && Dest[5]==4 && Dest[6]==1)
   @ && (Weight[0]==0 && Weight[1]==1 && Weight[2]==2 && Weight[3]==3 && Weight[4]==4 && Weight[5]==5 && Weight[6]==6)
   @ && (\forall int k;(k >= 0 && k < distance.length);distance[k] == 0);
   @ ensures 
   @ (distance[0] >= 0 && distance[1] >= 0 && distance[2] >= 0 && distance[3] >= 0 && distance[4] >= 0); 
   @*/

int bf(int[] distance,int[] Source,int[] Dest,int[] Weight,int nodecount){
  int INFINITY = 899;
  int edgecount = 7;
  int source = 0;
  int x,y,val;
  int i,j;

  i=0;
  while(i < nodecount){
    if(i == source){
      distance[i] = 0;
    }
    else {
      distance[i] = INFINITY;
    }
    i=i+1;
  }
  i=0;
  while(i < nodecount)
    { 
      j=0;
      while(j < edgecount)
        {
          x = Dest[j];
          y = Source[j];
          val = distance[y] - Weight[j]; // error in the assignment : should be "val = distance[y] + Weight[j];"
          if(distance[x] > val)
            {
              distance[x] = val;
            }
           j=j+1;
        } 
      i=i+1;
    }
  /*i=0;
  while(i < edgecount)
    {
      x = Dest[i];
      y = Source[i];
      val = distance[y] + Weight[i];
      if(distance[x] > val)
        {
          return 0;
        }
      i=i+1;
    }*/

  return 0;
}
}
