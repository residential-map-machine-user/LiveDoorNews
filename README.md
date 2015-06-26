# LiveDoorNews
1.プロジェクト直下に置いておいたwarをエクリプスにimport  

2.tomcat7のhomeをしていしてサーバを立ち上げる.  

3.立ち上げたところで次のurlにアクセスする  

4.http://localhost:8080/LiveDoorNews/front/news/  

5.ナビゲーションメニューから他のページへ移る  

6.インデックスを作成する機能をcron jobによってあぷりから分離してキャッシュ高速化

7.記事を取得するのもcron jobでデータベースに保存する

8.最初接続するときは１分くらいから現在は1秒以下に抑えた。

どんなプロジェクトなのか見たい場合はすでに公開しているので次のURLから  

9.一時間に一回更新してます。更新は**:30 のときに行うのでそのときにアクセスすると記事が表示されないことも
http://rss-reader-live.cloudapp.net/live2/front/news  

  
