package findGene;

//video Coding While Loops in lesson Finding All Genes in DNA

/**
 * Write a description of FindGeneWhile here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class FindGeneWhile {
    public String findGene(String dna) {
        //Find first occurrence of â€œATGâ€? call its index "startIndex"
        int startIndex = dna.indexOf("ATG");
        //Find the "TAA" starting from (startIndex+3), call this result currIndex
        int currIndex = dna.indexOf("TAA", startIndex + 3);
        //As long as currIndex is not equal to -1
        while (currIndex != -1) {
            //Check if (currIndex - startIndex) is a multiple of 3
            if((currIndex - startIndex) % 3 == 0) {
               //If so, the text between startIndex and currIndex + 3 is your answer
               return dna.substring(startIndex, currIndex + 3);
            }
            //If not, update currIndex to the index of the next TAA, starting from (currIndex+1)
            else {
                  currIndex = dna.indexOf("TAA", currIndex + 1);
            }
        }
        //Your answer is the empty string
        return "";
    }
    public String findGeneSimple(String dna) {
        // start codon is "ATG"
        // stop codon is "TAA"
        String result = "";
        int startIndex = dna.indexOf("ATG");
        if (startIndex == -1) {
            return "";
        }
        int stopIndex = dna.indexOf("TAA", startIndex + 3);
        result = dna.substring(startIndex, stopIndex + 3);

        return result;
    }    
    public void testFindGeneSimple() {
        //             v  v  v  v  v
        String dna = "AATGCGTAATTAATCG";
        System.out.println("DNA strand is " + dna);
        String gene = findGene(dna);
        System.out.println("Gene is " + gene);
        //       v  v  v  v  v  v  v  v
        dna = "CGATGGTTGATAAGCCTAAGCTATAA";
        System.out.println("DNA strand is " + dna);
        gene = findGene(dna);
        System.out.println("Gene is " + gene);
        //       v  v  v  v  v  v  v  v
        dna = "CGATGGTTGATAAGCCTAAGCTAAA";
        System.out.println("DNA strand is " + dna);
        gene = findGene(dna);
        System.out.println("Gene is " + gene);
    }
}
