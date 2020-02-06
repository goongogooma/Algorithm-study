package 실전기출;
/*
* Date: 2020.02.01
* 문제: BOJ 17822. 원판돌리기
* URL: https://www.acmicpc.net/problem/17822
* 유형: 시뮬레이션
* 문제 이해:
*  1. NxM인 원판에서 x의 배수에 해당하는 행을 d방향으로 k만큼 이동한다.
*  2. 인접한 수가 존재하는지 여부를 판별한다
*   -1. 존재한다면 인접한 수를 모두 지운다.
*   -2. 존재하지 않는다면 모든 수의 평균을 구하고 평균보다 큰 수는 -1, 작은 수는 +1을 더한다.
*  3. 1, 2과정을 T번 반복시행한다.
*  4. 원판에 남은 수의 합을 출력한다. 
* 반성할 점:
*  문제풀이가 손에 익지 않는다. 오래 쉬어서 그런 듯.
*  변수 이름 정하는 데에 불필요하게 시간을 오래 썼다.
*  불필요한 변수, 코드가 좀 있는 것 같음.
*  정수/실수 연산에서 실수했다.
* 소요 시간: 80분
*/
import java.util.Scanner;

public class Main17822_원판돌리기 {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);

		int N=sc.nextInt();
		int M=sc.nextInt();
		int T=sc.nextInt();

		int[][] field=new int[N][M];
		for (int i = 0; i < N; i++)
			for (int j = 0; j < M; j++)
				field[i][j]=sc.nextInt();

		int[][] xdk=new int[T][3];
		for (int i = 0; i < T; i++)
			for (int j = 0; j < 3; j++)
				xdk[i][j]=sc.nextInt();

		for (int cycle = 0; cycle < T; cycle++) {
			for (int i = 0; i < N; i++) {
				if((i+1)%xdk[cycle][0]==0) {
					int r=xdk[cycle][2]%M;;
					int[] tmpArr=new int[M];
					switch(xdk[cycle][1]) {
					case 0:
						for (int j = 0; j < M; j++) {
							int tmp=j+r;
							if(tmp>=M)
								tmp=tmp-M;
							tmpArr[tmp]=field[i][j];
						}
						break;
					case 1:
						for (int j = 0; j < M; j++) {
							int tmp=j-r;
							if(tmp<0)
								tmp=M+tmp;
							tmpArr[tmp]=field[i][j];
						}
						break;
					}
					for (int j = 0; j < M; j++)
						field[i][j]=tmpArr[j];
				}
			}
			int compareI;
			int compareJ;
			boolean flag=false;
			boolean[][] tmpField=new boolean[N][M];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if(field[i][j]>1000)
						continue;

					compareJ= (j==M-1) ? 0 : j+1;
					if(field[i][j]==field[i][compareJ]) {
						tmpField[i][j]=tmpField[i][compareJ]=true;
						flag=true;
					}

					compareI=i+1;
					if(compareI==N)
						continue;
					if(field[i][j]==field[compareI][j]) {
						tmpField[i][j]=tmpField[compareI][j]=true;
						flag=true;
					}
				}
			}

			if(flag) {
				for (int i = 0; i < N; i++)
					for (int j = 0; j < M; j++)
						if(tmpField[i][j])
							field[i][j]=1001;
			}
			else {
				int sum=0;
				int cnt=0;
				for (int i = 0; i < N; i++)
					for (int j = 0; j < M; j++)
						if(field[i][j]<=1000) {
							sum+=field[i][j];
							cnt++;
						}
				float avg=(float) (sum/(float)cnt);
				for (int i = 0; i < N; i++) {
					for (int j = 0; j < M; j++) {
						if(field[i][j]<=1000) {
							if(field[i][j]>avg)
								field[i][j]-=1;
							else if(field[i][j]<avg)
								field[i][j]+=1;
						}
					}
				}
			}
		}
		
		int answer=0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(field[i][j]<=1000)
					answer+=field[i][j];
			}
		}
		System.out.println(answer);
	}
}
