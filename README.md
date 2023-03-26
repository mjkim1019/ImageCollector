# ImageCollector
이미지를 검색해서 보관함에 수집하는 안드로이드 앱

## 📌 Getting Started
```
git clone 'https://github.com/mjkim1019/ImageCollector.git'
```

## 📌 주요 사용 기술
- **Paging3**: 무한 스크롤로 데이터 받아올 수 있게 구현
- **Glide**: 이미지 보여주기위한 라이브러리
- **DataBinding**: xml에서 데이터처리하기 위해 사용
- **BuildSrc Modulation**: AppConfig & Dependencies로 나눠서 모듈화
- **Coroutines**: Paging, Api 등 비동기 작업할 때 사용
- **Hilt**: apiService, viewModel 등에 DI 주입
- **Retrofit2**: api 작업
- **OkHttp3**: api 작업
- **SharedPreference**: 앱 내 영구적으로 저장하기 위해 사용

## 📌 Results
https://drive.google.com/file/d/13tYiwwf9cpeewiN9pa8L4xaWOEzkkLr_/view?usp=share_link
</br> 위 링크를 들어가시면 앱이 작동하는 모습을 볼 수 있습니다 :)

## 📌 기능 설명
### 🔍 검색 기능
- 검색어를 입력하면 이미지 검색과 동영상 검색을 동시에 진행합니다
    - 이미지 검색 API ( https://developers.kakao.com/docs/latest/ko/daum-search/dev-guide#search-image )
    - 동영상 검색 API ( https://developers.kakao.com/docs/latest/ko/daum-search/dev-guide#search-video )
- 검색 결과는 최신부터 나타납니다
- 각 아이템은 이미지 url(thumbnail), 날짜, 시간, 좋아요(보관) 여부을 표시합니다
- 스크롤을 통해 다음 페이지를 불러옵니다 (캐시 작업으로 원활한 스크롤이 진행됩니다)
- 이미 보관된 이미지는 분홍색 하트로 표시됩니다
- 각 아이템의 하트 버튼을 통해 보관 또는 제거 가능합니다

### 📥 보관 기능 (내 보관함)
- 상단 오른쪽의 하튼 버튼을 누르면 '내 보관함'으로 이동됩니다
- 보관한 이미지들은 보관한 순서대로 보입니다
- 하트 버튼을 누른 후 화면을 나가면 좋아요가 해제됩니다
- 앱을 재시작하여도 보관한 이미지는 그대로 남아있습니다

## 📌 Development Environment
```
Android Studio: Electric Eel | 2022.1.1 
Android Gradle Plugin: 7.4.1 
Gradle Version: 7.6
```

