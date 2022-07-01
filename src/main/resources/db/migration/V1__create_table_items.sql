-- items
create table items
(
    id                bigint auto_increment primary key  comment 'ID',
    item_token        varchar(255)  not null             comment 'item_token',
    item_no           varchar(6)    not null             comment '상품번호',
    item_name         varchar(255)  not null             comment '상품명',
    price             varchar(30)   not null             comment '판매가격',
    stock             int default 0 not null             comment '재고수량',
    status            varchar(20)       null             comment '재고상태'
);