package threeStopCodons;

//Video Coding Three Stop Codons part 2

/**
 * Finding three stop codons
 * 
 * @author Owen
 * @version 1.0
 */
public class AllCodons
{
    
    public int findStopCodon(String dnaStr,
                             int startIndex, 
                             String stopCodon){
            // find "TAA" starting from (startIndex + 3), call this result currIndex                     
            int currIndex = dnaStr.indexOf(stopCodon, startIndex + 3);
            // as long as currIndex is not equal to -1
            while (currIndex != -1) {
               // check if (currIndex - startIndex) is a multiple of 3
               int diff = currIndex - startIndex;
               // if so, the text between the 2 is your gene string
               if (diff % 3  == 0) {
                   return currIndex;
               }
               // if not, update the currIndex to the index of the next stopCodon, currIndex + 1
               else {
                   currIndex = dnaStr.indexOf(stopCodon, currIndex + 1);
               }
            }
            // if no gene string is found, return the full DNA string
            return dnaStr.length();
        
    }
    public String findGene(String dna) {
        int startIndex = dna.indexOf("ATG");
        if (startIndex == -1) {
            return "";
        }
        int taaIndex = findStopCodon(dna, startIndex,"TAA");
        int tagIndex = findStopCodon(dna, startIndex,"TAG");
        int tgaIndex = findStopCodon(dna, startIndex,"TGA");
        int temp = Math.min(taaIndex, tagIndex);
        int minIndex = Math.min(temp, tgaIndex);
        //int minIndex = Math.min(taaIndex,Math.min(tagIndex,tgaIndex));
        if (minIndex == dna.length()){
            return "";
        }
        
        return dna.substring(startIndex,minIndex + 3);
    }
    
    public void testFindStop() {
        //            01234567890123456789012345
        String dna = "xxxyyyzzzTAAxxxyyyzzzTAAxx";
        int dex = findStopCodon(dna,0,"TAA");
        if (dex != 9) System.out.println("error on 9");
        dex = findStopCodon(dna,9,"TAA");
        if (dex != 21) System.out.println("error on 21");
        dex = findStopCodon(dna,1,"TAA");
        if (dex != 26) System.out.println("error on 26");
        dex = findStopCodon(dna,0,"TAG");
        if (dex != 26) System.out.println("error on 26 TAG");
        System.out.println("tests finished");
    }

}
