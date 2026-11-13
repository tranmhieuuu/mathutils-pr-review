# Demo loi Checkstyle

De bot binh luan dung vi tri trong diff PR, sua file source THUC SU tren branch demo.

Trong `src/main/java/com/practice/math/MathUtils.java`, sau dong khai bao method `add`, chen:

```java
int BAD_variable = 0;
```

Sai quy tac `LocalVariableName` (bien phai la lowerCamelCase).
Khong luu file `.java` loi co san o thu muc demo, vi reviewer co the quet no va lam main hong.

Tao Pull Request branch `checkstyle-broken-demo` -> `main`, xem Files changed va Checks.
Khi muon sua, **xoa dong** sai, commit va push lai cung branch.
