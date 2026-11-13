# Bai 7 - Automated Code Review via Pull Request

Project ke thua MathUtils tu Bai 6, bo sung Checkstyle review bot cho Pull Request.

## 1. Cach thuc hoat dong

- `.github/workflows/ci.yml`: kich hoat khi `pull_request` huong vao `main`.
- Job **Checkstyle inline review** su dung `dbelyaev/action-checkstyle@v4` de dang binh luan ngay tren dong vi pham (trong phan diff cua PR).
- Job **Maven verify** chay unit tests, dong goi JAR, JaCoCo >= 80% va Maven Checkstyle.
- Ca hai su dung chung bo quy tac `config/checkstyle/checkstyle.xml` (bo quy uoc Java cua project, khong phai toan bo Google Java Style).
- `fail_level: any` de job review that bai khi co vi pham; Maven `checkstyle:check` trong `verify` de chan tat ca vi pham trong ma nguon, ke ca dong khong nam trong diff.

## 2. Mo va test trong IntelliJ

1. File -> Open -> chon thu muc `Bai07` chua `pom.xml`.
2. Mo Maven -> Execute Maven Goal -> nhap `clean verify`.
3. Mo Maven -> Execute Maven Goal -> nhap `dependency:copy-dependencies` de tao `lib/`.
4. Test `bash run.sh` trong Git Bash/WSL.

Khong su dung `target/` lam ma nguon. `run.sh` dung `lib/*.jar` nen can tai/copy dependency truoc khi nop.

## 3. Tao repository GitHub RIENG cho Bai 7

Tao repository trong tren GitHub, vi du `mathutils-pr-review`.
Nen dung **public repository** neu tai khoan GitHub Free, vi branch protection cho repository private co han che theo goi GitHub.
Khong commit password, token, key hoac thong tin ca nhan.

Mo Git Bash tai THU MUC GOC Bai07 (co `.github` va `pom.xml`):

```bash
git init
git branch -M main
git add .
git commit -m "Bai07: PR quality gate with Checkstyle"
git remote add origin https://github.com/USERNAME/mathutils-pr-review.git
git push -u origin main
```

Thay `USERNAME` bang tai khoan cua ban; repository can duoc tao truoc tren GitHub.
Neu `origin` da ton tai, dung `git remote set-url origin URL_CHINH_XAC`.
Workflow CHI chay khi co pull request vao main, nen push main chua lam workflow chay.

## 4. Tao Pull Request co loi co y

```bash
git switch -c checkstyle-broken-demo
```

Mo `src/main/java/com/practice/math/MathUtils.java`, trong phuong thuc `add`, them MOT DONG:

```java
int BAD_variable = 0;
```

Dat ngay sau `public static int add(int a, int b) {` (giua ngoac nhon), luu file.
Day la ten bien sai quy tac camelCase (vi pham `LocalVariableName`).
No khong thay doi phep tinh, nhung Checkstyle se bao loi.

```bash
git add src/main/java/com/practice/math/MathUtils.java
git commit -m "demo: introduce Checkstyle violation"
git push -u origin checkstyle-broken-demo
```

Vao GitHub -> Pull requests -> New pull request -> base `main`, compare `checkstyle-broken-demo` -> Create pull request.
PR duoc tao TRONG CUNG REPOSITORY, khong phai fork (GITHUB_TOKEN can `pull-requests: write` de dang review).

Mo tab **Files changed** de xem bot comment tren dong `BAD_variable`.
Mo tab **Checks** de xem `Checkstyle inline review` va `Maven verify` that bai.

## 5. Bat Branch Protection de khoa Merge

Tao rule SAU KHI da co run job, vi GitHub can nhin thay ten checks.

GitHub repository -> Settings -> Branches -> Add branch protection rule.
- Branch name pattern: `main`.
- Bat **Require a pull request before merging** (neu co tuy chon nay).
- Bat **Require status checks to pass before merging**.
- Chon DUNG HAI checks: `Checkstyle inline review` va `Maven verify`.
- Neu muon rule cung ap dung cho admin, bat **Do not allow bypassing the above settings**.
- Luu rule (co the phai xac nhan mat khau GitHub).

Tai lai PR: luc co loi, muc Merge se bi chan boi required checks.
Luu y: branch protection khong the duoc kich hoat chi bang file zip; can quyen quan tri repository.

## 6. Sua loi va kiem chung

Xoa dong `int BAD_variable = 0;`, luu file, sau do:

```bash
git add src/main/java/com/practice/math/MathUtils.java
git commit -m "fix: resolve Checkstyle violation"
git push
```

GitHub tu chay lai tren PR. Cho hai checks thanh cong roi kiem tra Merge duoc cho phep
(neu khong co quy tac chan khac). Khong bat buoc phai merge PR de nop minh chung.

## 7. Minh chung va nop bai

Luu anh vao `docs/` va dien cac link run/PR trong `docs/PR_EVIDENCE.md`:

1. `01_inline_comment.png`: bot binh luan tren dong BAD_variable trong Files changed.
2. `02_failed_checks.png`: CI do va trang Merge bi chan.
3. `03_branch_protection.png`: rule `main` yeu cau hai status checks.
4. `04_fixed_checks.png`: hai checks xanh sau khi sua.

Chi dien thong tin that, khong tu dien minh chung neu chua chay.

Nop trong thu muc `Bai07/`: `.github/`, `config/`, `src/`, `lib/`, `docs/`, `demo/`,
`pom.xml`, `run.sh`, `README.md`. Khong can `.idea/`, `.git/`, `target/`, `build/`.
Giao vien chay `./run.sh` tren JDK 25; thu lai tren Linux/WSL de xac minh truoc khi nop.

## Luu y

- PR tu fork co the khong du quyen `GITHUB_TOKEN` de bot post comment; demo tren branch cung repo.
- Reporter `github-pr-review` bao loi tren CAC DONG MOI SUA (added lines). Maven `verify` kiem tra TOAN BO code.
- Neu 2 check chua hien khi tao branch rule, hay chay it nhat 1 PR de GitHub ghi nhan job status.
- Neu khong thay Branch protection, kiem tra quyen admin va loai repository/plan GitHub.
