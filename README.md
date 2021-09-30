# [CommunityPG](http://aws-and-community-pg.ap-northeast-1.elasticbeanstalk.com/Login.html)

#### "CommunityPG" はポートフォリオとして作成した、社内コミュニケーションサイトです。

## URL
http://aws-and-community-pg.ap-northeast-1.elasticbeanstalk.com/Login.html/
<br><br>

# 機能一覧
ユーザー権限には「**User,Manager,Admin**」の3つが存在し、各画面で利用できる機能を制限しています。<br>
<br>

1. ***従業員検索画面***
   1. 全て空白の場合は全件検索を行います。
      - 入力値がある場合、その値であいまい検索を行います。<br><br>
　　 ![](https://cpp-learning.com/wp-content/uploads/2019/05/pyxel-190505-161951.gif)<br><br>
   2. ユーザーを押下すると、プロフィールが表示されます。
      - ログイン中のユーザー権限によって処理を制御しています。
        | 権限		| 変更			| 削除	| 
        |:---:		|:---:		|:---:|
        | **User**	| 自身の情報のみ〇 	| ✕ 	|
        | **Manager**	| 自身の情報のみ〇	| 〇 	|
        | **Admin**	| 全て〇		| 〇	| 
       ![](https://cpp-learning.com/wp-content/uploads/2019/05/pyxel-190505-161951.gif)<br><br>
<br>
        
2. ***電子会議室***
   1. トピックを作成することができます。
   2. 作成されたトピックに対して、投稿をする事ができます。
   3. 投稿に対して、Like・Bad ボタンを押し評価することができます。（※ 1件の投稿に対し、1件まで）
   4. トピック・投稿を編集することができます。
      - ログイン中のユーザー権限によって処理を制御しています。
        | 権限         | 変更            | 削除            | 
        |:-----------:|:---------------:|:--------------:|
        | **User**    | 自身の投稿のみ〇 | 自身の投稿のみ〇 |
        | **Manager** | 自身の投稿のみ〇 | 〇              |
        | **Admin**   | 全て〇          | 〇              | 
       ![](https://cpp-learning.com/wp-content/uploads/2019/05/pyxel-190505-161951.gif)<br><br>
<br>      
       

3. ***ファイル共有***
   1. フォルダの作成や削除、ファイル共有などが行えます。
   2. フォルダ内に入ったり、前のフォルダに戻ったり、ファイルを保存したりできます。<br><br>
     ![](https://cpp-learning.com/wp-content/uploads/2019/05/pyxel-190505-161951.gif)<br><br>
<br><br>




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

# Author
* Kouya Ajima

# License
[MIT license](https://en.wikipedia.org/wiki/MIT_License).


# Internal-Community-Web
