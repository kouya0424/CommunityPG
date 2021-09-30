# [CommunityPG](http://aws-and-community-pg.ap-northeast-1.elasticbeanstalk.com/Login.html)

[CommunityPG](http://aws-and-community-pg.ap-northeast-1.elasticbeanstalk.com/Login.html)はポートフォリオとして作成した、社内コミュニケーションサイトです。
<br><br>

# 機能一覧
ユーザー権限には「**User,Manager,Admin**」の3つが存在し、各画面で利用できる機能を制限しています。<br>
<br><br>

1. ***従業員検索画面***
   1. 全て空白の場合は全件検索を行います。
      - 入力値がある場合、その値であいまい検索を行います。
　　    <br><br>![](https://cpp-learning.com/wp-content/uploads/2019/05/pyxel-190505-161951.gif)<br><br>
   2. ユーザーを押下すると、プロフィールが表示されます。
      - 下記の通り、ログイン中のユーザー権限によって処理を制御。
        | 権限       | 制限                                                      |
        |:-----------|:---------------------------------------------------------|
        | User     　| 自身のプロフィール以外は変更・削除権限なし                   |
        | Manager    | ユーザーの削除権限あり。自身のプロフィール以外は変更権限なし  |
        | Admin      | 全ての変更権限があり                                      |
       ![](https://cpp-learning.com/wp-content/uploads/2019/05/pyxel-190505-161951.gif)<br><br>

        
        
        
        
2. ***電子会議室***

![](https://cpp-learning.com/wp-content/uploads/2019/05/pyxel-190505-161951.gif)

- ファイル共有
> フォルダの作成や削除、ファイルの共有などが行えます。
![](https://cpp-learning.com/wp-content/uploads/2019/05/pyxel-190505-161951.gif)


This animation is a "Cat playing on trampoline"!
You can get basic skills for making physics simulations.

# Features

Physics_Sim_Py used [pyxel](https://github.com/kitao/pyxel) only.

```python
import pyxel
```
[Pyxel](https://github.com/kitao/pyxel) is a retro game engine for Python.
You can feel free to enjoy making pixel art style physics simulations.

# Requirement

* Python 3.6.5
* pyxel 1.0.2

Environments under [Anaconda for Windows](https://www.anaconda.com/distribution/) is tested.

```bash
conda create -n pyxel pip python=3.6 Anaconda
activate pyxel
```

# Installation

Install Pyxel with pip command.

```bash
pip install pyxel
```

# Usage

Please create python code named "demo.py".
And copy &amp; paste [Day4 tutorial code](https://cpp-learning.com/pyxel_physical_sim4/).

Run "demo.py"

```bash
python demo.py
```

# Note

I don't test environments under Linux and Mac.

# Author

* Hayabusa
* R&D Center
* Twitter : https://twitter.com/Cpp_Learning

# License

"Physics_Sim_Py" is under [MIT license](https://en.wikipedia.org/wiki/MIT_License).

Enjoy making cute physics simulations!

Thank you!
