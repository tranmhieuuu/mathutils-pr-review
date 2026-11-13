# Minh chung Bai 7 - PR Automated Code Review

- Repository: (dien link that)
- Pull Request demo: (dien link that)
- Workflow run bao loi: (dien link that)
- Workflow run sau sua: (dien link that)

## 1. Bot dang nhan xet vao dong code vi pham

Chen `int BAD_variable = 0;` vao `MathUtils.add` -> vi pham Checkstyle `LocalVariableName`.

![Inline comment](01_inline_comment.png)

## 2. Hai kiem tra / trang Merge khi code sai

`Checkstyle inline review` va/hoac `Maven verify` that bai -> branch protection chan merge.

![Failed checks](02_failed_checks.png)

## 3. Branch protection rule main

Require status checks: `Checkstyle inline review` va `Maven verify`.

![Branch protection](03_branch_protection.png)

## 4. Sau khi sua ten bien/loai bo dong sai

CI chay thanh cong -> required checks dat, neu khong co quy tac khac thi Merge duoc mo.

![Fixed checks](04_fixed_checks.png)

> Chi giu cac muc va anh thuc te da chup. Khong nhan da kiem chung neu chua thuc hien tren GitHub.
