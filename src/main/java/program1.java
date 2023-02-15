public class program1 {
    public static void main(String[] args) {
        System.out.println(maxScionValue(
                new int[]{5,7,6,3,9},
                new int[]{7,9,2,7,5},
                new int[]{1,9,9,3,3},
                new int[]{8,4,1,10,5},
                new int[]{6,7,1,4,11}
        ));
    }
//    public int maxLength(String strs[]){
//        int maxLength=0;
//        String healthString=null;
//        for(int i=0;i<strs.length;i++){
//            int t=strs[i].length();
//            if(strs[i].charAt(0)==strs[i].charAt(t-1)) {
//                if (healthString!= null) {
//                    healthString="";
//                }
//                healthString+=strs[i];
//                maxLength+=t;
//            }
//        }
//    }

    private static int maxScionValue(int A[],int B[],int C[],int D[],int E[]){
        int[] preFixSum=new int[A.length];
        int max=0;

        for(int i=0;i<A.length;i++){
            preFixSum[i]=A[i]+B[i]+C[i]+D[i]+E[i];
        }

        for(int left=0;left<A.length;left++){
            for(int right=left+1;right<A.length;right++) {
                if (Math.abs(preFixSum[left] - preFixSum[right]) > max) {
                    max = Math.abs(preFixSum[left] - preFixSum[right]);
                }
            }

        }

        return max;
    }
}
