package Gui;

public class maxgraph {


	  
    int vertices;
    
    public  maxgraph(int v) {
   	 this.vertices=v;
   	 
    }
// for better understanding take one set of vertices as applications and other side as jobs to be assigned
   // A DFS based recursive function that
   // returns true if a matching for
   // vertex u is possible
   boolean bpm(boolean bpGraph[][], int u,
               boolean seen[], int matchR[])
   {
       
       for (int v = 0; v < vertices; v++)
       {
        
           if (bpGraph[u][v] && !seen[v])
           {
                
               // Mark v as visited
               seen[v] = true;

     
               if (matchR[v] < 0 || bpm(bpGraph, matchR[v],
                                        seen, matchR))
               {
                   matchR[v] = u;
                   return true;
               }
           }
       }
       return false;
   }

   // Returns maximum number
   // of matching from one vertex set to other 
   int maxBPM(boolean bpGraph[][])
   {
       // An array to keep track of the
       // applicants assigned 
       // The value of matchR[i] is the
       // applicant number assigned to job i,
       // the value -1 indicates nobody is assigned.
       int matchR[] = new int[vertices];

       // Initially all vertices are available
       for(int i = 0; i < vertices; ++i)
           matchR[i] = -1;

       // Count of jobs(right vertices) assigned to applicants
       int result = 0;
       for (int u = 0; u < vertices; u++)
       {
           // Mark all vertices (jobs) as not seen
           // for next applicant.
           boolean seen[] =new boolean[vertices] ;
           for(int i = 0; i < vertices; ++i)
               seen[i] = false;

           // Find if the applicant 'u' can get a job
           if (bpm(bpGraph, u, seen, matchR))
               result++;
       }
       return result;
   }


}