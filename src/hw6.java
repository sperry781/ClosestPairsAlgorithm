package algorithms2;
import java.io.File;
import java.util.Scanner;
import java.util.Arrays;
import java.io.FileNotFoundException;
import java.lang.Math;

public class hw6 {

	public static void main(String[] args) throws FileNotFoundException
	{
		System.out.println("Start");
		File tenPoints = new File("C:\\Users\\Admin\\Desktop\\AlgorithmsHW\\10points.txt");
		File hundredPoints = new File("C:\\Users\\Admin\\Desktop\\AlgorithmsHW\\100points.txt");
		File thousandPoints = new File("C:\\Users\\Admin\\Desktop\\AlgorithmsHW\\1000points.txt");
		System.out.println("Files created");
		int[] tenPointsArrayX = new int[10];
		int[] hundredPointsArrayX = new int[100];
		int[] thousandPointsArrayX = new int[1000];
		int[] tenPointsArrayY = new int[10];
		int[] hundredPointsArrayY = new int[100];
		int[] thousandPointsArrayY = new int[1000];
		int[] tenPointsArray = new int[20];
		int[] hundredPointsArray = new int[200];
		int[] thousandPointsArray = new int[2000];
		System.out.println("Arrays created");
		
		tenPointsArray = createArrayFromTextFile(tenPoints, 10);
		for(int i = 0; i<20; i++)
		{
			if(i%2==0)
				tenPointsArrayX[i/2]=tenPointsArray[i];
			else
				tenPointsArrayY[i/2]=tenPointsArray[i];
		}
		System.out.println("first arrays created");
		System.out.println(Arrays.toString(tenPointsArrayX));
		System.out.println(Arrays.toString(tenPointsArrayY));

		hundredPointsArray = createArrayFromTextFile(hundredPoints, 100);
		for(int i = 0; i<200; i++)
		{
			if(i%2==0)
				hundredPointsArrayX[i/2]=hundredPointsArray[i];
			else
				hundredPointsArrayY[i/2]=hundredPointsArray[i];
		}
		System.out.println("second arrays created");
		System.out.println(Arrays.toString(hundredPointsArrayX));
		System.out.println(Arrays.toString(hundredPointsArrayY));
		
		thousandPointsArray = createArrayFromTextFile(thousandPoints, 1000);
		for(int i = 0; i<2000; i++)
		{
			if(i%2==0)
				thousandPointsArrayX[i/2]=thousandPointsArray[i];
			else
				thousandPointsArrayY[i/2]=thousandPointsArray[i];
		}
		System.out.println("third arrays created");
		System.out.println(Arrays.toString(thousandPointsArrayX));
		System.out.println(Arrays.toString(thousandPointsArrayY));
		
		int[] tenPointsArrayClosestPair = new int[4];
		tenPointsArrayClosestPair = closestPair(tenPointsArrayX, tenPointsArrayY, 10);
		System.out.println(Arrays.toString(tenPointsArrayClosestPair));
		
		int[] hundredPointsArrayClosestPair = new int[4];
		hundredPointsArrayClosestPair = closestPair(hundredPointsArrayX, hundredPointsArrayY, 100);
		System.out.println(Arrays.toString(hundredPointsArrayClosestPair));
		
		int[] thousandPointsArrayClosestPair = new int[4];
		thousandPointsArrayClosestPair = closestPair(thousandPointsArrayX, thousandPointsArrayY, 1000);
		System.out.println(Arrays.toString(thousandPointsArrayClosestPair));
		
		
		
		
		
	}
	
	
	public static int[] createArrayFromTextFile(File file, int size) throws FileNotFoundException
	{
		Scanner sc = new Scanner(file);
		int[] textArray = new int[size*2];
		int i = 0;
		while(sc.hasNextLine())
		{
			String value = sc.next();
			System.out.println(value);
			textArray[i] = Integer.parseInt(value);
			i++;
		}
		sc.close();
		return textArray;
	}
	
	public static int[] insertionSortPosition(int[] A, int n)
	{
		int[] sortedArray = A;
		int[] positions = new int[n];
		for(int i = 0; i<n; i++)
		{
			positions[i]=i;
		}
		return insertionSortHelperPosition(sortedArray, n, 1, positions);
	}
	
	public static int[] insertionSortHelperPosition(int[] A, int n, int pos, int[] positions)
	{
		if(pos<n)
		{
			for(int i = pos; i>0; i--)
			{
				if(A[i]<A[i-1])
				{
					int temp = A[i-1];
					int temp2 = positions[i-1];
					A[i-1]=A[i];
					A[i]=temp;
					positions[i-1]=positions[i];
					positions[i]=temp2;
				}
			}
			return insertionSortHelperPosition(A, n, pos+1, positions);
		}
		else
			return positions;
	}
	
	public static int[] closestPair(int[] coordX, int[] coordY, int n)
	{
		int[] sortedPosX = insertionSortPosition(coordX, n);
		
		if(n==2)
		{
			int[] two = new int[4];
			two[0] = coordX[0];
			two[1] = coordY[0];
			two[2] = coordX[1];
			two[3] = coordY[1];
			return two;
		}
		if(n==3)
		{
			int[] three = new int[4];
			int distanceZeroToOne = (int)Math.sqrt(((coordX[0]-coordX[1])^2)+((coordY[0]-coordY[1])^2));
			int distanceZeroToTwo = (int)Math.sqrt(((coordX[0]-coordX[2])^2)+((coordY[0]-coordY[2])^2));
			int distanceOneToTwo = (int)Math.sqrt(((coordX[1]-coordX[2])^2)+((coordY[1]-coordY[2])^2));
			if((distanceZeroToOne<distanceZeroToTwo)&(distanceZeroToOne<distanceOneToTwo))
			{
				three[0] = coordX[0];
				three[1] = coordY[0];
				three[2] = coordX[1];
				three[3] = coordY[1];
				return three;
			}
			else if((distanceZeroToTwo<distanceZeroToOne)&(distanceZeroToTwo<distanceZeroToTwo))
			{
				three[0] = coordX[0];
				three[1] = coordY[0];
				three[2] = coordX[2];
				three[3] = coordY[2];
				return three;
			}
			else
			{
				three[0] = coordX[1];
				three[1] = coordY[1];
				three[2] = coordX[2];
				three[3] = coordY[2];
				return three;
			}
		}
		
		
		int middle = n/2;
		
		int[] leftArrayX = new int[middle];
		int[] leftArrayY = new int[middle];
		
		int[] rightArrayX = new int[n-middle];
		int[] rightArrayY = new int[n-middle];
		//creates a left and right array, each in sorted x order
		for(int i = 0; i<middle; i++)
		{
			leftArrayX[i]=coordX[sortedPosX[i]];
			leftArrayY[i]=coordY[sortedPosX[i]];
		}
		
		for(int i = 0; i<n-middle; i++)
		{
			rightArrayX[i]=coordX[sortedPosX[i+middle]];
			rightArrayY[i]=coordY[sortedPosX[i+middle]];
		}
		//these will be of size 4, first two are first pair, second two are second pair
		int[] closestPairLeft = closestPairHelper(leftArrayX, leftArrayY, middle);
		int[] closestPairRight = closestPairHelper(rightArrayX, rightArrayY, n-middle);
		double closestPairLeftDistance = Math.sqrt(((closestPairLeft[0]-closestPairLeft[2])^2)+((closestPairLeft[1]-closestPairLeft[3])^2));
		double closestPairRightDistance = Math.sqrt(((closestPairRight[0]-closestPairRight[2])^2)+((closestPairRight[1]-closestPairRight[3])^2));
		int[] closestPair = new int[4];
		int closestPairDistance;
		if(closestPairLeftDistance>closestPairRightDistance)
		{
			closestPair=closestPairRight;
			closestPairDistance=(int)closestPairRightDistance;
		}
		else
		{
			closestPair=closestPairLeft;
			closestPairDistance=(int)closestPairLeftDistance;
		}
		int pointerLeft = middle;
		int pointerRight = middle;
		while((coordX[sortedPosX[pointerLeft]]>(coordX[sortedPosX[middle]]-closestPairDistance))&(pointerLeft!=0))
		{
			pointerLeft--;
		}
		
		while((coordX[sortedPosX[pointerRight]]<(coordX[sortedPosX[middle]]+closestPairDistance))&(pointerRight!=n-1))
		{
			pointerRight++;
		}
		int middleClosestPairDistance = (int)Math.sqrt(((coordX[sortedPosX[pointerLeft+1]]-coordX[sortedPosX[pointerLeft]])^2)+((coordY[sortedPosX[pointerLeft+1]]-coordY[sortedPosX[pointerLeft]])^2));
		int[] middlePair = new int[4];
		
		
		int[] newSortedPosY1 = new int[pointerRight-pointerLeft];
		int[] newSortedPosY2 = new int[pointerRight-pointerLeft];
		int[] sortedPosY= new int[pointerRight-pointerLeft];
		for(int i = 0; i<pointerRight-pointerLeft; i++)
		{
			newSortedPosY1[i]= coordX[sortedPosX[pointerLeft+i]];
			newSortedPosY2[i]= coordY[sortedPosX[pointerLeft+i]];
		}
		sortedPosY=insertionSortPosition(newSortedPosY2, pointerRight-pointerLeft);
		
		middlePair[0]=coordX[sortedPosX[pointerLeft+1]];
		middlePair[1]=coordY[sortedPosX[pointerLeft+1]];
		middlePair[2]=coordX[sortedPosX[pointerLeft]];
		middlePair[3]=coordY[sortedPosX[pointerLeft]];
		pointerRight--;
		System.out.println("left pointer is "+pointerLeft);
		System.out.println("right pointer is "+pointerRight);
		for(int i = pointerLeft; i<pointerRight; i++)
		{
			
			for(int t = pointerLeft+1; t<pointerRight; t++)
			{
				int tempPairDistance = (int)Math.sqrt(((newSortedPosY1[sortedPosY[t]]-newSortedPosY1[sortedPosY[i]])^2)+((newSortedPosY2[sortedPosY[t]]-newSortedPosY2[sortedPosY[i]])^2));
				if(tempPairDistance<middleClosestPairDistance)
				{
					middleClosestPairDistance=tempPairDistance;
					middlePair[0]=coordX[sortedPosX[t]];
					middlePair[1]=coordY[sortedPosX[t]];
					middlePair[2]=coordX[sortedPosX[i]];
					middlePair[3]=coordY[sortedPosX[i]];
				}
			}
		}
		if(middleClosestPairDistance<closestPairDistance)
		{
			closestPair=middlePair;
			closestPairDistance=middleClosestPairDistance;
		}
		
		return closestPair;
	}
	//This is already sorted by x, so I don't need sortedPosX
	public static int[] closestPairHelper(int[] coordX, int[] coordY, int n)
	{
		if(n==2)
		{
			int[] two = new int[4];
			two[0] = coordX[0];
			two[1] = coordY[0];
			two[2] = coordX[1];
			two[3] = coordY[1];
			System.out.println("Helper two pair found");
			return two;
		}
		if(n==3)
		{
			int[] three = new int[4];
			int distanceZeroToOne = (int)Math.sqrt(((coordX[0]-coordX[1])^2)+((coordY[0]-coordY[1])^2));
			int distanceZeroToTwo = (int)Math.sqrt(((coordX[0]-coordX[2])^2)+((coordY[0]-coordY[2])^2));
			int distanceOneToTwo = (int)Math.sqrt(((coordX[1]-coordX[2])^2)+((coordY[1]-coordY[2])^2));
			if((distanceZeroToOne<distanceZeroToTwo)&(distanceZeroToOne<distanceOneToTwo))
			{
				three[0] = coordX[0];
				three[1] = coordY[0];
				three[2] = coordX[1];
				three[3] = coordY[1];
				System.out.println("Helper three pair found");
				return three;
			}
			else if((distanceZeroToTwo<distanceZeroToOne)&(distanceZeroToTwo<distanceZeroToTwo))
			{
				three[0] = coordX[0];
				three[1] = coordY[0];
				three[2] = coordX[2];
				three[3] = coordY[2];
				System.out.println("Helper three pair found");
				return three;
			}
			else
			{
				three[0] = coordX[1];
				three[1] = coordY[1];
				three[2] = coordX[2];
				three[3] = coordY[2];
				System.out.println("Helper three pair found");
				return three;
			}
		}
		
		
		int middle = n/2;
		
		int[] leftArrayX = new int[middle];
		int[] leftArrayY = new int[middle];
		
		int[] rightArrayX = new int[n-middle];
		int[] rightArrayY = new int[n-middle];
		//creates a left and right array, each in sorted x order
		for(int i = 0; i<middle; i++)
		{
			leftArrayX[i]=coordX[i];
			leftArrayY[i]=coordY[i];
		}
		
		for(int i = 0; i<n-middle; i++)
		{
			rightArrayX[i]=coordX[i];
			rightArrayY[i]=coordY[i];
		}
		
		int[] closestPairLeft = closestPairHelper(leftArrayX, leftArrayY, middle);
		int[] closestPairRight = closestPairHelper(rightArrayX, rightArrayY, n-middle);
		double closestPairLeftDistance = Math.sqrt(((closestPairLeft[0]-closestPairLeft[2])^2)+((closestPairLeft[1]-closestPairLeft[3])^2));
		double closestPairRightDistance = Math.sqrt(((closestPairRight[0]-closestPairRight[2])^2)+((closestPairRight[1]-closestPairRight[3])^2));
		int[] closestPair = new int[4];
		int closestPairDistance;
		if(closestPairLeftDistance>closestPairRightDistance)
		{
			closestPair=closestPairRight;
			closestPairDistance=(int)closestPairRightDistance;
		}
		else
		{
			closestPair=closestPairLeft;
			closestPairDistance=(int)closestPairLeftDistance;
		}
		
		System.out.println("helper middle is "+middle);
		int pointerLeft; 
		pointerLeft = middle;
		int pointerRight;
		pointerRight=middle;
		while((coordX[pointerLeft]>=(coordX[middle]-closestPairDistance))&(pointerLeft!=0))
		{
			pointerLeft--;
		}
		
		while((coordX[pointerRight]<(coordX[middle]+closestPairDistance))&(pointerRight!=n-1))
		{
			pointerRight++;
		}
		int middleClosestPairDistance = (int)Math.sqrt(((coordX[pointerLeft+1]-coordX[pointerLeft])^2)+((coordY[pointerLeft+1]-coordY[pointerLeft])^2));
		int[] middlePair = new int[4];
		

		int[] newSortedPosY1 = new int[pointerRight-pointerLeft];
		int[] newSortedPosY2 = new int[pointerRight-pointerLeft];
		int[] sortedPosY= new int[pointerRight-pointerLeft];
		for(int i = 0; i<pointerRight-pointerLeft; i++)
		{
			newSortedPosY1[i]= coordX[pointerLeft+1];
			newSortedPosY2[i]= coordY[pointerLeft+i];
		}
		sortedPosY=insertionSortPosition(newSortedPosY2, pointerRight-pointerLeft);
		
		middlePair[0]=coordX[pointerLeft+1];
		middlePair[1]=coordY[pointerLeft+1];
		middlePair[2]=coordX[pointerLeft];
		middlePair[3]=coordY[pointerLeft];
		System.out.println("helper left pointer is "+pointerLeft);
		System.out.println("helper right pointer is "+pointerRight);
		pointerRight--;
		for(int i = pointerLeft; i<pointerRight; i++)
		{
			
			for(int t = pointerLeft+1; t<pointerRight; t++)
			{
				System.out.println("t is "+t);
				System.out.println("i is "+i);
				int tempPairDistance = (int)Math.sqrt(((newSortedPosY1[sortedPosY[t]]-newSortedPosY1[sortedPosY[i]])^2)+((newSortedPosY2[sortedPosY[t]]-newSortedPosY2[sortedPosY[i]])^2));
				if(tempPairDistance<middleClosestPairDistance)
				{
					middleClosestPairDistance=tempPairDistance;
					middlePair[0]=coordX[t];
					middlePair[1]=coordY[t];
					middlePair[2]=coordX[i];
					middlePair[3]=coordY[i];
				}
			}
		}
		if(middleClosestPairDistance<closestPairDistance)
		{
			closestPair=middlePair;
			closestPairDistance=middleClosestPairDistance;
		}
		System.out.println("Helper pair found");
		return closestPair;
		
	}
}

