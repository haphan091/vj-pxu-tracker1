# VJC → PXU Alert

Android app theo dõi live flight data từ Flightradar24 API và gửi thông báo khi phát hiện chuyến bay:

- operator VJC (hoặc callsign VJC*/flight VJ*)
- origin HAN hoặc SGN
- destination PXU

## Lưu ý dữ liệu
FR24 API live chỉ trả về chuyến bay khi tàu bay đang được hệ thống tracking nhận thấy; API không phải nguồn lịch bay scheduled.

## Background
Android AlarmManager được dùng để kiểm tra khoảng 15 phút/lần và tự lập lịch lại sau mỗi lần chạy. Hệ điều hành có thể trì hoãn tác vụ nền để tiết kiệm pin.

## Build
Mở project bằng Android Studio rồi Build > Generate App Bundle / APK > Generate APK.

Hoặc với Gradle:
`./gradlew assembleRelease`

APK nằm ở `app/build/outputs/apk/release/`.

## Token
Không nhúng FR24 token vào source/APK. Người dùng nhập token trong ứng dụng; token lưu cục bộ trên thiết bị.

## GitHub Actions
Mỗi lần push lên `main`, workflow sẽ tự build release APK và lưu APK thành artifact. Có thể chạy thủ công bằng `workflow_dispatch`.
