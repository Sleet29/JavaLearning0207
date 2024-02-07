-- 실행 방법
-- 1. 한 문장 실행의 경우는 커서를 문장위에 놓고 마우스 우클릭 -> Execute Current Text(Alt + S)
drop table goodsinfo purge;


-- 2. 여러 문장 실행의 경우
--	  영역 설정 후 마우스 우클릭 -> Execute Selected Text (Alt + X)
--								또는
--								Execute Selected Text As One Statement(Alt+C)
create table goodsinfo(
	code varchar2(5) primary key,
	name varchar2(30) not null,
	price number(8) not null,
	maker varchar2(20)
);

/*
 CODE   NAME 		PRICE   MAKER
 -----  -----   	------  ------
 10001  디지털 TV		350000  LG
 10002  DVD 플레이어	250000  LG
 10003  디지털 카메라	210000  삼성
 10004  전자사전		180000  아이리버
 10005  벽걸이 에어컨	400000  LG
 */
insert into goodsinfo
values('10001', '디지털 TV', 350000, 'LG');

insert into goodsinfo
values('10002', 'DVD 플레이어', 250000, 'LG');

insert into goodsinfo
values('10003', '디지털 카메라', 210000, '삼성');

insert into goodsinfo
values('10004', '전자사전', 180000, '아이리버');

insert into goodsinfo
values('10005', '벽걸이 에어컨', 400000, 'LG');

select * from GOODSINFO;

-- 3. 함수나 프로시저 처럼 세미콜론으로 끝나는 여러 문장이 포함되는 경우
-- 	  영역 설정 후 마우스 우클릭 -> Execute selected Text As One Statement(Alt+C)

create or replace function working_years(vempno emp.empno%type)
	return number
is
	vyear number;
begin
	select floor((sysdate-hiredate)/365)
	into vyear
	from emp
	where empno=vempno;
	return vyear;
end;


select working_years(7788) from dual;
