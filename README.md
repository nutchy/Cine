
<p align="center">
  <a href="https://play.google.com/store/apps/details?id=me.nutchy.cine">
    <img alt="Cine" title="Cine" src="https://firebasestorage.googleapis.com/v0/b/cine-apps.appspot.com/o/cinelogo512.png?alt=media&token=b275de82-c5e1-4e99-8853-8a849810cf66" width="180">
  </a>
  <h1 align="center"> Cine </h1> <br>
</p>

<h4 align="center">An application for rating movies</h4>
<p align="center">
  <a href="https://play.google.com/store/apps/details?id=me.nutchy.cine">
    <img src="https://img.shields.io/badge/version-1.0-brightgreen.svg" alt="GitHub version" height="18"></a>
  <a href="https://badge.fury.io/gh/nutchy%2Ffinal_project_android">
    <img src="https://img.shields.io/badge/gradlew-3.0.1-brightgreen.svg" alt="GitHub version" height="18"></a>
  <a href="https://badge.fury.io/gh/nutchy%2Ffinal_project_android">
    <img src="https://img.shields.io/badge/build-passing-brightgreen.svg" alt="GitHub version" height="18"></a>
</p>
<p align="center">
  <a href="https://play.google.com/store/apps/details?id=me.nutchy.cine">
    <img alt="Get it on Google Play" title="Google Play" src="http://i.imgur.com/mtGRPuM.png" width="140">
  </a>
</p>


<!-- START doctoc generated TOC please keep comment here to allow auto update -->
<!-- DON'T EDIT THIS SECTION, INSTEAD RE-RUN doctoc TO UPDATE -->
## Table of Contents

- [Feature](#feature)
- [User Interface](#user-interface)
- [API](#api)
- [Video](#video)
<!-- END doctoc generated TOC please keep comment here to allow auto update -->


## Feature
- Login by Facebook Account
- แสดงรายการหนังที่ดึงได้จาก API
- ให้คะแนนหนังที่ชื่นชอบ
- แสดงความคิดเห็นในหนังเรื่องที่ต้องการ
- เพิ่มหนังที่ชอบเป็นรายการโปรด"


## User Interface


## API
### 1. Facebook SDK



### 2. The Movie Database Api
```xml
   BASE_URL: "https://api.themoviedb.org/"
```

- ดึงรายการภาพยนตร์ที่กำลังจะเข้าโรง
```
GET /movie/upcoming

https://api.themoviedb.org/3/movie/upcoming?api_key=<< api_key >>

Parameter : -
```
``` json
{
  "results": [
    {
      "vote_count": 541,
      "id": 440021,
      "video": false,
      "vote_average": 6.4,
      "title": "Happy Death Day",
      "popularity": 527.981098,
      "poster_path": "/cTaEIUYTt52ooq9quVbAQ7NpGwo.jpg",
      "original_language": "en",
      "original_title": "Happy Death Day",
      "genre_ids": [
        27
      ],
      "backdrop_path": "/eGx5OfOdvM0gkHdmkLe3hcJuEIT.jpg",
      "adult": false,
      "overview": "A college student relives the day of her murder over and over again as she tries to discover her killer's identity.",
      "release_date": "2017-10-12"
    },
    {
      "vote_count": 336,
      "id": 298250,
      "video": false,
      "vote_average": 5.9,
      "title": "Jigsaw",
      "popularity": 495.481667,
      "poster_path": "/zUbUtxiTdEgWnkXY945gtYYqBZ1.jpg",
      "original_language": "en",
      "original_title": "Jigsaw",
      "genre_ids": [
        27,
        53
      ],
      "backdrop_path": "/ytKpFaLMpFWnuSXStz1GHrtTt6R.jpg",
      "adult": false,
      "overview": "Dead bodies begin to turn up all over the city, each meeting their demise in a variety of grisly ways. All investigations begin to point the finger at deceased killer John Kramer.",
      "release_date": "2017-10-20"
    },
    {
      "vote_count": 165,
      "id": 419680,
      "video": false,
      "vote_average": 5.5,
      "title": "Daddy's Home 2",
      "popularity": 357.44399,
      "poster_path": "/rF2IoKL0IFmumEXQFUuB8LajTYP.jpg",
      "original_language": "en",
      "original_title": "Daddy's Home 2",
      "genre_ids": [
        18,
        35
      ],
      "backdrop_path": "/lMDyuHzBhx3zNAv48vYzmgcJCCD.jpg",
      "adult": false,
      "overview": "Brad and Dusty must deal with their intrusive fathers during the holidays.",
      "release_date": "2017-11-09"
    }
  ],
  "page": 1,
  "total_results": 319,
  "dates": {
    "maximum": "2017-12-23",
    "minimum": "2017-11-29"
  },
  "total_pages": 16
}
```

- ดึงรายการภาพยนต์ที่กำลังฉาย
```
GET /movie/now_playing

https://api.themoviedb.org/3/movie/now_playing?api_key=<< api_key >>

Parameter : 
```
``` json
{
  "results": [
    {
      "vote_count": 2070,
      "id": 284053,
      "video": false,
      "vote_average": 7.5,
      "title": "Thor: Ragnarok",
      "popularity": 743.969937,
      "poster_path": "/oSLd5GYGsiGgzDPKTwQh7wamO8t.jpg",
      "original_language": "en",
      "original_title": "Thor: Ragnarok",
      "genre_ids": [
        28,
        12,
        35,
        14,
        878
      ],
      "backdrop_path": "/5wNUJs23rT5rTBacNyf5h83AynM.jpg",
      "adult": false,
      "overview": "Thor is imprisoned on the other side of the universe and finds himself in a race against time to get back to Asgard to stop Ragnarok, the prophecy of destruction to his homeworld and the end of Asgardian civilization, at the hands of an all-powerful new threat, the ruthless Hela.",
      "release_date": "2017-10-25"
    },
    {
      "vote_count": 1082,
      "id": 141052,
      "video": false,
      "vote_average": 6.9,
      "title": "Justice League",
      "popularity": 649.745562,
      "poster_path": "/9rtrRGeRnL0JKtu9IMBWsmlmmZz.jpg",
      "original_language": "en",
      "original_title": "Justice League",
      "genre_ids": [
        28,
        12,
        14,
        878
      ],
      "backdrop_path": "/o5T8rZxoWSBMYwjsUFUqTt6uMQB.jpg",
      "adult": false,
      "overview": "Fueled by his restored faith in humanity and inspired by Superman's selfless act, Bruce Wayne and Diana Prince assemble a team of metahumans consisting of Barry Allen, Arthur Curry, and Victor Stone to face the catastrophic threat of Steppenwolf and the Parademons who are on the hunt for three Mother Boxes on Earth.",
      "release_date": "2017-11-15"
    },
    {
      "vote_count": 541,
      "id": 440021,
      "video": false,
      "vote_average": 6.4,
      "title": "Happy Death Day",
      "popularity": 527.981098,
      "poster_path": "/cTaEIUYTt52ooq9quVbAQ7NpGwo.jpg",
      "original_language": "en",
      "original_title": "Happy Death Day",
      "genre_ids": [
        27
      ],
      "backdrop_path": "/eGx5OfOdvM0gkHdmkLe3hcJuEIT.jpg",
      "adult": false,
      "overview": "A college student relives the day of her murder over and over again as she tries to discover her killer's identity.",
      "release_date": "2017-10-12"
    }
  ],
  "page": 1,
  "total_results": 1145,
  "dates": {
    "maximum": "2017-12-05",
    "minimum": "2017-10-18"
  },
  "total_pages": 58
}
```
- ดึงรายการภาพยนตร์ยอดนิยม
```
GET /movie/popular

https://api.themoviedb.org/3/movie/popular?api_key=<< api_key >>

Parameter : - 
```
``` json
{
  "page": 1,
  "total_results": 19837,
  "total_pages": 992,
  "results": [
    {
      "vote_count": 4111,
      "id": 346364,
      "video": false,
      "vote_average": 7.3,
      "title": "It",
      "popularity": 874.849543,
      "poster_path": "/9E2y5Q7WlCVNEhP5GiVTjhEhx1o.jpg",
      "original_language": "en",
      "original_title": "It",
      "genre_ids": [
        18,
        14,
        27,
        53
      ],
      "backdrop_path": "/tcheoA2nPATCm2vvXw2hVQoaEFD.jpg",
      "adult": false,
      "overview": "In a small town in Maine, seven children known as The Losers Club come face to face with life problems, bullies and a monster that takes the shape of a clown called Pennywise.",
      "release_date": "2017-09-05"
    },
    {
      "vote_count": 2070,
      "id": 284053,
      "video": false,
      "vote_average": 7.5,
      "title": "Thor: Ragnarok",
      "popularity": 743.969937,
      "poster_path": "/oSLd5GYGsiGgzDPKTwQh7wamO8t.jpg",
      "original_language": "en",
      "original_title": "Thor: Ragnarok",
      "genre_ids": [
        28,
        12,
        35,
        14,
        878
      ],
      "backdrop_path": "/5wNUJs23rT5rTBacNyf5h83AynM.jpg",
      "adult": false,
      "overview": "Thor is imprisoned on the other side of the universe and finds himself in a race against time to get back to Asgard to stop Ragnarok, the prophecy of destruction to his homeworld and the end of Asgardian civilization, at the hands of an all-powerful new threat, the ruthless Hela.",
      "release_date": "2017-10-25"
    },
    {
      "vote_count": 1082,
      "id": 141052,
      "video": false,
      "vote_average": 6.9,
      "title": "Justice League",
      "popularity": 649.745562,
      "poster_path": "/9rtrRGeRnL0JKtu9IMBWsmlmmZz.jpg",
      "original_language": "en",
      "original_title": "Justice League",
      "genre_ids": [
        28,
        12,
        14,
        878
      ],
      "backdrop_path": "/o5T8rZxoWSBMYwjsUFUqTt6uMQB.jpg",
      "adult": false,
      "overview": "Fueled by his restored faith in humanity and inspired by Superman's selfless act, Bruce Wayne and Diana Prince assemble a team of metahumans consisting of Barry Allen, Arthur Curry, and Victor Stone to face the catastrophic threat of Steppenwolf and the Parademons who are on the hunt for three Mother Boxes on Earth.",
      "release_date": "2017-11-15"
    }
  ]
}
```

- ดึงข้อมูลของภาพยนตร์แต่ละเรื่อง

```
GET /movie/{movie_id}

https://api.themoviedb.org/3/movie/346364?api_key=<< api_key >>

Parameter : {movie_id}

```
``` json
{
  "adult": false,
  "backdrop_path": "/tcheoA2nPATCm2vvXw2hVQoaEFD.jpg",
  "belongs_to_collection": null,
  "budget": 35000000,
  "genres": [
    {
      "id": 18,
      "name": "Drama"
    },
    {
      "id": 14,
      "name": "Fantasy"
    },
    {
      "id": 27,
      "name": "Horror"
    },
    {
      "id": 53,
      "name": "Thriller"
    }
  ],
  "homepage": "http://itthemovie.com/",
  "id": 346364,
  "imdb_id": "tt1396484",
  "original_language": "en",
  "original_title": "It",
  "overview": "In a small town in Maine, seven children known as The Losers Club come face to face with life problems, bullies and a monster that takes the shape of a clown called Pennywise.",
  "popularity": 873.849543,
  "poster_path": "/9E2y5Q7WlCVNEhP5GiVTjhEhx1o.jpg",
  "production_companies": [
    {
      "name": "New Line Cinema",
      "id": 12
    },
    {
      "name": "Vertigo Entertainment",
      "id": 829
    },
    {
      "name": "Lin Pictures",
      "id": 2723
    },
    {
      "name": "RatPac-Dune Entertainment",
      "id": 41624
    },
    {
      "name": "KatzSmith Productions",
      "id": 87671
    }
  ],
  "production_countries": [
    {
      "iso_3166_1": "US",
      "name": "United States of America"
    }
  ],
  "release_date": "2017-09-05",
  "revenue": 555575232,
  "runtime": 135,
  "spoken_languages": [
    {
      "iso_639_1": "da",
      "name": "Dansk"
    }
  ],
  "status": "Released",
  "tagline": "Your fears are unleashed",
  "title": "It",
  "video": false,
  "vote_average": 7.3,
  "vote_count": 4108
}
```
- แสดงรายการหนังแนะนำ
```
GET /movie/{movie_id}/recommendations

https://api.themoviedb.org/3/movie/{movie_id}/recommendations?api_key=<< api_key >>

Parameter : movid_id
```

### 3. Firebase
ใช้ Firebase สำหรับการเก็บข้อมูลอื่นๆเพิ่มเติมเช่น ข้อมูลการแสดงความคิดเห็น , การให้คะแนน , ข้อมูลผู้ใช้ที่ได้จาก Facebook, เก็บหนังที่ชื่นชอบของผู้ใช้งาน อีกทั้งยังเป็นส่วนช่วยในการ Authentication ในการเพิ่มข้อมูล/โหวตคะแนน

## Video 
ส่งภายในวันที่ 4

