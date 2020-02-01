package 실전기출;

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
