# LCS

문제: 9251
날짜: 2024년 3월 27일
상태: 완료
티어: 골드 5

### 1. 문제 설명

[9251번: LCS](https://www.acmicpc.net/problem/9251)

### 2. 구현 방법

[최장 공통 부분 수열(LCS)](https://nutritious-wildflower-988.notion.site/LCS-ebf29f17106f4868a21e4d85c3e442ad?pvs=4)

### 3. 회고

- 문자열은 0부터 시작 주의!!
- LCS 구현 방식 외우기!!

### 4. 코드

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Bj_9251_LCS {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String str1 = br.readLine();
		String str2 = br.readLine();

		int[][] LCS = new int[str1.length()+1][str2.length()+1];

		for (int i = 1; i <= str1.length(); i++) {
			char alpa = str1.charAt(i-1);
			for (int j = 1; j <= str2.length(); j++) {
				
				// 비교 -1 더해서 해주는 이유 : 문자열은 0부터 시작함!!
				// 문자가 같을 경우 -> 바로 전의 공통 부분 수열의 개수 +1을 해줌
				if(alpa == str2.charAt(j-1)) LCS[i][j] = LCS[i-1][j-1] +1;
				
				// 문자가 다를 경우 -> 바로 전의 str1의 앞 부분 수열 또는 str2의 앞 부분수열 중 큰 것 선택
				else LCS[i][j] = Math.max(LCS[i-1][j], LCS[i][j-1]);
				
			}
		}
		System.out.println(LCS[str1.length()][str2.length()]);
	}
}

```