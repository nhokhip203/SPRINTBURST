create database SPRINT_BURST;
go

use SPRINT_BURST;
go

create table NhanVien
(
    id          int identity (1,1) primary key,
    maTaiKhoan  varchar(max),
    email       varchar(max),
    dienThoai   varchar(max),
    password    varchar(max),
    hoTen       nvarchar(max),
    diaChi      nvarchar(max),
    ghiChu      nvarchar(max),
    laQuanLy    bit,
    ngayTao     datetime,
    ngayCapNhat datetime,
    ngayXoa     datetime,
    trangThai   bit
);
go

create table KhachHang
(
    id           int identity (1,1) primary key,
    maKhachHang  varchar(max),
    tenKhachHang nvarchar(max),
    email        varchar(max),
    dienThoai    varchar(max),
    diaChi       nvarchar(max),
    ghiChu       nvarchar(max),
    ngayTao      datetime,
    idNhanVien   int,
    foreign key (idNhanVien) references NhanVien (id)
);
go

create table SanPham
(
    id          int identity (1,1) primary key,
    tenSanPham  nvarchar(max),
    maSanPham   varchar(max),
    ngayTao     datetime,
    ngayCapNhat datetime,
    ngayXoa     datetime,
    trangThai   bit
);
go

create table ThuongHieu
(
    id  int identity (1,1) primary key,
    ten nvarchar(max)
);
go

create table XuatXu
(
    id  int identity (1,1) primary key,
    ten nvarchar(max)
);
go

create table ChatLieu
(
    id  int identity (1,1) primary key,
    ten nvarchar(max)
);
go

create table DeGiay
(
    id  int identity (1,1) primary key,
    ten nvarchar(max)
);
go

create table CoGiay
(
    id  int identity (1,1) primary key,
    ten nvarchar(max)
);
go

create table MauSac
(
    id  int identity (1,1) primary key,
    ten nvarchar(max)
);
go

create table Size
(
    id     int identity (1,1) primary key,
    sizeVn nvarchar(max)
);
go

create table SanPhamChiTiet
(
    id                int identity (1,1) primary key,
    idSanPham         int,
    maSanPhamChiTiet  varchar(max),
    tenSanPhamChiTiet nvarchar(max),
    moTa              nvarchar(max),
    ngayTao           datetime,
    ngayCapNhat       datetime,
    ngayXoa           datetime,
    trangThai         bit,
    giaBan            int,
    soLuong           int,
    idThuongHieu      int,
    idXuatXu          int,
    idChatLieu        int,
    idDeGiay          int,
    idCoGiay          int,
    idMauSac          int,
    idSize            int,
    foreign key (idSanPham) references SanPham (id),
    foreign key (idThuongHieu) references ThuongHieu (id),
    foreign key (idXuatXu) references XuatXu (id),
    foreign key (idChatLieu) references ChatLieu (id),
    foreign key (idDeGiay) references DeGiay (id),
    foreign key (idCoGiay) references CoGiay (id),
    foreign key (idMauSac) references MauSac (id),
    foreign key (idSize) references Size (id),
);
go

create table PhieuGiamGia
(
    id             int identity (1,1) primary key,
    maGiamGia      varchar(max),
    tenGiamGia     nvarchar(max),
    giaTriToiThieu int,
    giamPhanTram   float,
    soGiamToiDa    int,
    ngayBatDau     datetime,
    ngayKetThuc    datetime,
    ngayTao        datetime,
    trangThai      bit
);
go

create table TrangThaiHoaDon
(
    id  int identity (1,1) primary key,
    ten nvarchar(max)
);
go

create table HoaDon
(
    id                 int identity (1,1) primary key,
    maHoaDon           varchar(max),
    idKhachHang        int,
    idNhanVien         int,
    ngayTao            datetime,
    tongTruocGiamGia   int,
    tongSauGiamGia     int,
    ghiChu             nvarchar(max),
    tenNguoiNhan       nvarchar(max),
    dienThoaiNguoiNhan varchar(max),
    diaChiNguoiNhan    nvarchar(max),
    idTrangThaiHoaDon  int,
    idPhieuGiamGia     int,
    foreign key (idKhachHang) references KhachHang (id),
    foreign key (idNhanVien) references NhanVien (id),
    foreign key (idTrangThaiHoaDon) references TrangThaiHoaDon (id),
    foreign key (idPhieuGiamGia) references PhieuGiamGia (id)
);
go

create table HoaDonChiTiet
(
    id               int identity (1,1) primary key,
    idHoaDon         int,
    idSanPhamChiTiet int,
    soLuong          int,
    giaBan           int,
    trangThai        bit,
    foreign key (idHoaDon) references HoaDon (id),
    foreign key (idSanPhamChiTiet) references SanPhamChiTiet (id)
);
go

create table ThanhToan
(
    id            int identity (1,1) primary key,
    idHoaDon      int,
    ngayThanhToan datetime,
    soTienMat     int,
    soTienChuyen  int,
    foreign key (idHoaDon) references HoaDon (id)
);
go

create table LichSuHoaDon
(
    id          int identity (1,1) primary key,
    idHoaDon    int,
    idNhanVien  int,
    ngayTacDong datetime,
    ghiChu      nvarchar(max),
    foreign key (idHoaDon) references HoaDon (id),
    foreign key (idNhanVien) references NhanVien (id)
);
go
