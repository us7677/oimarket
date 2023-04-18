drop database if exists jsp_project;
create database jsp_project;
use jsp_project;

drop table if exists member;
create table member(
	mno			int auto_increment primary key ,	-- 회원 고유번호
    mname 		varchar(20) not null ,				-- 회원 이름
    mid 		varchar(30) not null unique ,		-- 회원 아이디
    mpwd 		varchar(20) not null ,				-- 회원 비밀번호
    mresidence	varchar(40) not null ,				-- 회원 사는장소 
    mmw			varchar(10) ,						-- 회원 성별
    mphone		varchar(20) not null unique ,		-- 회원 핸드폰번호
    mimg		longtext 							-- 회원 프로필
);

-- 물품 카테고리 
drop table if exists product_category;
create table product_category(
	pcno 		int auto_increment primary key , 	-- 물품 카테고리번호
    pcname		varchar(30)							-- 물품 카테고리명
);

-- 물품
drop table if exists product;
create table product(
	pno			int	auto_increment primary key , 	-- 물품 고유번호
    ptitle		varchar(30) ,						-- 물품 제목
    pcontent	longtext ,							-- 물품 내용
    pprice		int , 								-- 물품 가격
    pview		int default 1, 								-- 조회수
    plat		varchar(30) ,						-- 위도
    plng		varchar(30) ,						-- 경도
    pdate		datetime default now() ,			-- 등록일
    pstate		int default 1,								-- 물품상태	[ 1 : 판매 가능 / 2 : 판매 완료 ]
    buydate		varchar(30) ,						-- 구매일
    rmno		int ,								-- 판매등록한 회원번호
    buymno		int ,								-- 구매한 회원번호
    pcno		int ,								-- 카테고리번호
	foreign key ( rmno ) references  member ( mno ) on delete cascade ,
    foreign key ( buymno ) references  member ( mno ) on delete cascade ,
    foreign key ( pcno ) references  product_category ( pcno ) on delete cascade 
);

-- 제품 사진
drop table if exists product_img;
create table product_img(
	pimgno		int	auto_increment primary key ,	-- 제품 사진번호
    pimgname	longtext ,							-- 제품 사진이름
    pno			int ,								-- 제품 번호
    foreign key ( pno ) references  product ( pno ) on delete cascade  
);

-- 제품 찜하기
drop table if exists product_like;
create table product_like(
	plikeno		int	auto_increment primary key ,	-- 제품 찜하기번호
    mno			int ,								-- 찜함 회원번호
	pno			int	,								-- 찜한 물품번호
	foreign key ( mno ) references  member ( mno ) on delete cascade ,
	foreign key ( pno ) references  product ( pno ) on delete cascade 
);

drop table if exists chatcategory;
create table chatcategory(
	cno bigint auto_increment primary key, 		-- 채팅방 번호 
    pno int,									-- 채팅방 제품 
    cstate int default 1,										
    mno1 int ,	-- 로그인한 사람
    mno2 int , -- 판매등록한사람
	foreign key ( mno1 ) references  member ( mno ) on delete cascade ,
    foreign key ( mno2 ) references  member ( mno ) on delete cascade, 
    foreign key (pno) references product(pno) on delete cascade
);

drop table if exists chat;
create table chat (
	nno			int  auto_increment primary key , 	-- 채팅 번호
    ncontent	longtext ,							-- 채팅 내용
    ndate		datetime default now() ,			-- 채팅 보낸 시간
    cno			bigint	,								-- 채팅 번호
    frommno		int	,								-- 보내는 사람
    tomno		int ,								-- 받는 사람
	foreign key ( cno ) references  chatcategory ( cno ) on delete cascade ,  
	foreign key ( tomno ) references  member ( mno ) on delete cascade ,
    foreign key ( frommno ) references  member ( mno ) on delete cascade 
);

select * from chat;


-- member 임의 값 대입
/*
insert into member values ( '1' , '최성아1' , 'asd1' , 'asd123' , '인천' , '남자' , '01022222222' , 'asd123');
insert into member values ( '2' , '최성아2' , 'asd2' , 'asd123' , '서울' , '남자' , '01033333333' , 'asd123');
insert into member values ( '3' , '최성아3' , 'asd3' , 'asd123' , '서울' , '여자' , '01044444444' , 'asd123');
insert into member values ( '4' , '최성아4' , 'asd4' , 'asd123' , '인천' , '남자' , '01055555555' , 'asd123');
insert into member values ( '5' , '최성아5' , 'asd5' , 'asd123' , '부산' , '여자' , '01066666666' , 'asd123');
*/

select * from member;

-- ----------------------------------------------------------------------------------------------

-- product_category 임의 값 대입

insert into product_category values ( '1' , '생활가전' );
insert into product_category values ( '2' , '의류' );
insert into product_category values ( '3' , '뷰티미용' );
insert into product_category values ( '4' , '가공식품' );
insert into product_category values ( '5' , '식물' );

select * from product_category;

-- ----------------------------------------------------------------------------------------------

-- product 임의 값 대입
/* insert into product values ( '1' , '생활가전팝니다.' , '싸게팝니다.' , 30000 , 2 , '31.3434' , '127.123' , '2023-03-30' , '1' , '2023-03-31' , '1' , '2' , '1' );
insert into product values ( '2' , '의류팝니다..' , '싸게팝니다.' , 40000 , 3 , '31.3434' , '127.123' , '2023-04-01' , '1' , '2023-04-02' , '2' , '3' , '2' );
insert into product values ( '3' , '뷰티미용팝니다.' , '싸게팝니다.' , 50000 , 4 , '31.3434' , '127.123' , '2023-04-03' , '1' , '2023-04-04' , '3' , '4' , '3' );
insert into product values ( '4' , '가공식품팝니다.' , '싸게팝니다.' , 60000 , 5 , '31.3434' , '127.123' , '2023-04-05' , '1' , '2023-04-06' , '4' , '5' , '4' );
insert into product values ( '5' , '식물팝니다.' , '싸게팝니다.' , 70000 , 6 , '31.3434' , '127.123' , '2023-04-07' , '1' , '2023-04-08' , '2' , '3' , '5' );
insert into product values ( '6' , '생활가전팝니다.' , '싸게팝니다.' , 30000 , 2 , '31.3434' , '127.123' , '2023-03-30' , '1' , '2023-03-31' , '1' , '2' , '1' );
insert into product values ( '7' , '의류팝니다..' , '싸게팝니다.' , 40000 , 3 , '31.3434' , '127.123' , '2023-04-01' , '1' , '2023-04-02' , '2' , '3' , '2' );
insert into product values ( '8' , '뷰티미용팝니다.' , '싸게팝니다.' , 50000 , 4 , '31.3434' , '127.123' , '2023-04-03' , '1' , '2023-04-04' , '3' , '4' , '3' );
insert into product values ( '9' , '가공식품팝니다.' , '싸게팝니다.' , 60000 , 5 , '31.3434' , '127.123' , '2023-04-05' , '1' , '2023-04-06' , '4' , '5' , '4' );
insert into product values ( '10' , '식물팝니다.' , '싸게팝니다.' , 70000 , 6 , '31.3434' , '127.123' , '2023-04-07' , '1' , '2023-04-08' , '2' , '3' , '5' );
insert into product values ( '11' , '의류팝니다..' , '싸게팝니다.' , 40000 , 3 , '31.3434' , '127.123' , '2023-04-01' , '1' , '2023-04-02' , '2' , '3' , '2' );
insert into product values ( '12' , '뷰티미용팝니다.' , '싸게팝니다.' , 50000 , 4 , '31.3434' , '127.123' , '2023-04-03' , '1' , '2023-04-04' , '3' , '4' , '3' );
insert into product values ( '13' , '가공식품팝니다.' , '싸게팝니다.' , 60000 , 5 , '31.3434' , '127.123' , '2023-04-05' , '1' , '2023-04-06' , '4' , '5' , '4' );
insert into product values ( '14' , '식물팝니다.' , '싸게팝니다.' , 70000 , 6 , '31.3434' , '127.123' , '2023-04-07' , '1' , '2023-04-08' , '2' , '3' , '5' );
insert into product values ( '15' , '생활가전팝니다.' , '싸게팝니다.' , 30000 , 2 , '31.3434' , '127.123' , '2023-03-30' , '1' , '2023-03-31' , '1' , '2' , '1' );
insert into product values ( '17' , '생활가전팝니다.' , '싸게팝니다.' , 30000 , 2 , '31.3434' , '127.123' , '2023-04-11' , '2' , '2023-03-31' , '1' , '3' , '1' );
insert into product values ( '20' , '생활가전팝니다.' , '싸게팝니다.' , 30000 , 2 , '31.3434' , '127.123' , '2023-04-11' , '2' , '2023-04-11' , '1' , '3' , '1' );
insert into product values ( '21' , '생활가전팝니다.' , '싸게팝니다.' , 30000 , 2 , '31.3434' , '127.123' , '2023-04-11' , '2' , '2023-04-11' , '1' , '3' , '2' );
insert into product values ( '22' , '생활가전팝니다.' , '싸게팝니다.' , 30000 , 2 , '31.3434' , '127.123' , '2023-04-11' , '2' , '2023-04-11' , '1' , '3' , '3' );
*/


select * from product where pno=14;
select p.*,c.pcname from product p natural join product_category c  order by  p.pdate desc;
select p.*,c.pcname from product p natural join product_category c where ptitle like '%뷰티%';
select p.*,c.pcname from product p natural join product_category c where pno=1;
select * from member;
select p.*,c.pcname from product p natural join product_category c where pno=1;
select * from product_img where pno=1;

-- ----------------------------------------------------------------------------------------------

-- product_like 임의 값 대입
/*
insert into product_like values ( '1' , '1' , '1' );
insert into product_like values ( '2' , '1' , '1' );
insert into product_like values ( '3' , '1' , '3' );
insert into product_like values ( '4' , '1' , '4' );
insert into product_like values ( '5' , '2' , '1' );
insert into product_like values ( '6' , '3' , '3' );
insert into product_like values ( '7' , '4' , '4' );
insert into product_like values ( '8' , '5' , '5' );
insert into product_like values ( '9' , '3' , '5' );
insert into product_like values ( '10' , '4' , '5' );
*/
select * from product_like;

-- ----------------------------------------------------------------------------------------------

-- product_img 임의 값 대입
/*
insert into product_img values ( '1' , '생활가전img1' , '1' ); 
insert into product_img values ( '2' , '생활가전img2' , '1' ); 
insert into product_img values ( '3' , '생활가전img3' , '1' ); 
insert into product_img values ( '4' , '의류img' , '2' ); 
insert into product_img values ( '5' , '뷰티img' , '3' ); 
*/
select * from product_img;

-- ----------------------------------------------------------------------------------------------
/*
연습
select * from member;
select * from product_category;
select * from product;
select * from product_like;
select * from product_img;
select * from chat ;
select * from chat where pno=1 and (frommno=1 and tomno=6) or(frommno=6 and tomno=1);
-- 로그인한 사람[mno] 의 채팅목록 
select * from chat where pno=? and (frommno=? and tomno=?);
--
select * from chat where pno=3 and (frommno=5 and tomno=5);			-- 3번 제품의 로그인한 5번 회원의 채팅목록 [ 받거나 보냈거나 ]
select * from chat where pno=21 and (frommno=10 and tomno=10);		-- 21번 제품의 로그인한  10번 회원의 채팅목록 [ 받거나 보냈거나 ]
select * from chat where pno=18 and (frommno=3 and tomno=3);		-- 18번 제품의 로그인한 3번 회원의 채팅목록 [ 받거나 보냈거나 ]
-- 로그인 사람의 전체 채팅목록 
select * from chat where (frommno=1 and tomno=1);					-- 로그인한 1번 회원의 전체 채팅목록 [ 받거나 보냈거나 ]
-- 현재 본인 채팅에 포함된 레코드를 모두 찾자 --> java에서 제품별로 하나씩만 거르자 또는 출력하자
select * from chat where (frommno=1 and tomno=1) order by ndate desc;					-- 로그인한 1번 회원의 전체 채팅목록 [ 받거나 보냈거나 ]

select  nno , ncontent , pno , frommno , mid from chat c natural join member m where ( c.frommno=1 and c.tomno=1)  order by c.ndate desc;					-- 로그인한 1번 회원의 전체 채팅목록 [ 받거나 보냈거나 ]

*/


drop table if exists bdcategory;
create table bdcategory(
	bcno	int auto_increment primary key,	-- 게시판 카테고리 번호
    bcname	varchar(20)	-- 게시판 카테고리의 카테고리명
);
insert into bdcategory(bcno , bcname) values(1 , '게시판카테고리1');
insert into bdcategory(bcno , bcname) values(2 , '게시판카테고리2');
insert into bdcategory(bcno , bcname) values(3 , '게시판카테고리3');
insert into bdcategory(bcno , bcname) values(4 , '게시판카테고리4');

select * from bdcategory;

-- 게시물 테이블 [ 번호 , 제목 , 내용 , 첨부파일 , 작성일 , 조회수 , 좋아요수 , 싫어요수 , 작성자 ]
drop table if exists board;
create table board(
	bno			int auto_increment primary key,  -- 게시물번호
    btitle		varchar(1000) not null, -- 게시물 제목
    bcontent	longtext,  -- 게시물 내용
    bfile		longtext,  -- 첨부파일
    bdate		datetime default now(),  -- 등록일
    bview		int default 0, -- 조회수
    bup			int default 0, -- 좋아요
    bdown		int default 0, -- 시러요
    mno			int, -- 회원번호 fk
	bcno			int, -- 카테고리번호 fk
    foreign key(mno) references member(mno) on delete cascade,
    foreign key(bcno) references bdcategory(bcno) on delete cascade
);
/*
insert into board(bno , btitle , bcontent , bfile , bdate , bview , bup , bdown , mno , bcno )
values(1 , '테스트게시물1' , '1번게시물내용' , '게시물첨부파일1' , '20230330' , 10 , 8 , 2 , 1 , 1 );

insert into board(bno , btitle , bcontent , bfile , bdate , bview , bup , bdown , mno , bcno )
values(2 , '테스트게시물2' , '2번게시물내용' , '게시물첨부파일2' , '20230331' , 11 , 3 , 7 , 2 , 2 );

insert into board(bno , btitle , bcontent , bfile , bdate , bview , bup , bdown , mno , bcno )
values(3 , '테스트게시물3' , '3번게시물내용' , '게시물첨부파일3' , '20230329' , 13 , 15 , 6 , 3 , 3 );

insert into board(bno , btitle , bcontent , bfile , bdate , bview , bup , bdown , mno , bcno )
values(4 , '테스트게시물4' , '4번게시물내용' , '게시물첨부파일4' , '20230320' , 14 , 16 , 4 , 4 , 4 );
*/
select * from board;


-- 댓글 테이블 [ 댓글번호 , 내용 , 작성일 , 인덱스(계층구분) , 작성자(*누가) , 게시물번호(*어디에다가)  ]
drop table if exists reply;
create table reply(
	rno			int auto_increment primary key,  -- 댓글번호
    rcontent	longtext,  						 -- 댓글내용
    rdate		datetime default now(),			 -- 작성시간
    rindex		int default 0,					 -- 댓글인덱스(계층구분용도) , 0이면 최상위 , 1~ 해당 댓글[부모]의  하위 댓글
    mno			int,  							 -- 댓글작성자(누가)
    bno			int,   							 -- 게시물번호(어디 게시글에다가)
    foreign key(mno) references member(mno) on delete cascade,
    foreign key(bno) references board(bno) on delete cascade
);

/*
insert into reply(rno , rcontent , rdate , rindex , mno , bno ) values(1 , '테스트댓글1' , '20230330' , 0 , 1 , 1 );
insert into reply(rno , rcontent , rdate , rindex , mno , bno ) values(2 , '테스트댓글2' , '20230331' , 0 , 2 , 2 );
insert into reply(rno , rcontent , rdate , rindex , mno , bno ) values(3 , '테스트댓글3' , '20230401' , 0 , 3 , 3 );
insert into reply(rno , rcontent , rdate , rindex , mno , bno ) values(4 , '테스트댓글4' , '20230402' , 0 , 4 , 4 );
insert into reply(rno , rcontent , rdate , rindex , mno , bno ) values(5 , '테스트댓글3' , '20230401' , 1 , 3 , 3 );
insert into reply(rno , rcontent , rdate , rindex , mno , bno ) values(6 , '테스트댓글4' , '20230402' , 1 , 4 , 4 );
*/
select * from reply;
