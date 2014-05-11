# Sadari Example

매우 간단한 사다리 게임의 자바 구현입니다.
랜덤하게 사다리를 만들고 특정 라인에서 출발했을때 끝까지 가는 길을 찾아낼 수 있습니다.

## 이게 뭐죠?

다음과 같은 프로그램을 만들면 랜덤하게 사다리를 만들 수 있습니다.

    public final class SadariMain {
        public static void main(String[] args) {
        Sadari sadari = new SadariBuilder()
                .height(10)
                .lineCount(4)
                .generateBridgesRandomly()
                .build();

        System.out.println("Randomly Generated Ladder:");
        System.out.println(sadari.draw());

        System.out.println("Path of First Line:");
            List<Point> path = sadari.getPath(0);
            for (Point point : path) {
                System.out.println(point);
            }
        }
    }


대략적인 출력은 다음과 같습니다.

    Randomly Generated Ladder:
    |        |        |        |
    |--------|        |--------|
    |        |--------|        |
    |--------|        |--------|
    |--------|        |        |
    |        |--------|        |
    |--------|        |--------|
    |        |--------|        |
    |        |--------|        |
    |        |--------|        |
    |        |        |        |

    Path of First Line:
    Point{x=0, y=0}
    Point{x=0, y=1}
    Point{x=1, y=1}
    Point{x=1, y=2}
    Point{x=2, y=2}
    Point{x=2, y=3}
    Point{x=3, y=3}
    Point{x=3, y=6}
    Point{x=2, y=6}
    Point{x=2, y=7}
    Point{x=1, y=7}
    Point{x=1, y=8}
    Point{x=2, y=8}
    Point{x=2, y=9}
    Point{x=1, y=9}

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
