# Sadari Example

매우 간단한 사다리 게임의 자바 구현입니다.
랜덤하게 사다리를 만들고 특정 

## 이게 뭐죠?

다음과 같은 프로그램을 만들면 랜덤하게 사다리를 만들 수 있습니다.

	public final class SadariMain {

	    public static void main(String[] args) {
	        Sadari sadari = new SadariBuilder()
	                .height(10)
	                .lineCount(4)
	                .addRandomBridges(10).build();
	        System.out.println(sadari.draw());

	        List<Point> path = sadari.getPath(1);
	        for (Point point : path) {
	            System.out.println(point);
	        }
	    }
	}


대략적인 출력은 다음과 같습니다.

	|        |        |        |
	|--------|        |        |
	|        |        |        |
	|--------|        |--------|
	|        |--------|        |
	|--------|        |--------|
	|        |        |--------|
	|        |        |--------|
	|        |--------|        |
	|--------|        |        |
	|        |        |        |

	Point{x=1, y=0}
	Point{x=1, y=1}
	Point{x=0, y=1}
	Point{x=0, y=3}
	Point{x=1, y=3}
	Point{x=1, y=4}
	Point{x=2, y=4}
	Point{x=2, y=5}
	Point{x=3, y=5}
	Point{x=3, y=6}
	Point{x=2, y=6}
	Point{x=2, y=7}
	Point{x=3, y=7}

## License

별도의 표기가 없는 경우 아래의 라이센스에 따라 코드를 사용해주세요.

	/*
	 *             DO WHAT THE FUCK YOU WANT TO PUBLIC LICENSE 
	 *                     Version 2, December 2004
	 * 
	 *  Copyright (C) 2013 James Lee <roth2520@gmail.com>
	 *   
	 *  Everyone is permitted to copy and distribute verbatim or modified
	 *  copies of this license document, and changing it is allowed as long
	 *  as the name is changed.
	 * 
	 *             DO WHAT THE FUCK YOU WANT TO PUBLIC LICENSE
	 *    TERMS AND CONDITIONS FOR COPYING, DISTRIBUTION AND MODIFICATION
	 * 
	 *   0. You just DO WHAT THE FUCK YOU WANT TO. 
	 */
	 
[git]: http://git-scm.com/
[m2e]: http://www.eclipse.org/m2e/
[m2e-android]: http://rgladwell.github.io/m2e-android/
[eclipse]: http://www.eclipse.org/downloads/
[Android SDK]: http://developer.android.com/sdk/index.html