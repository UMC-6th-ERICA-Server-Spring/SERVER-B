-- 1.내가 진행중, 진행 완료한 미션 모아서 보는 쿼리(페이징 포함)

SELECT 
    m.id,
    m.store_id,
    m.points,
    m.deadline,
    m.mission_spec,
    mm.status,
    mm.created_at AS mission_assigned_at
FROM 
    mission AS m
RIGHT JOIN 
    member_mission AS mm ON m.id = mm.mission_id
WHERE 
    mm.member_id = 1 -- 임의로 지정
    AND m.deadline > NOW()
ORDER BY 
    mm.created_at DESC
LIMIT y OFFSET (x - 1) * y ; -- 임의로 지정

-- 2.리뷰 작성하는 쿼리, * 사진의 경우는 일단 배제

INSERT INTO (id, member_id, store_id, body, score) values (1, 4, 1, "음식이 맛있어요", 5);
INSERT INTO (id, member_id, store_id, body, score) values (2, 7, 2, "직원이 친절해요", 5);


-- 3.홈 화면 쿼리(현재 선택 된 지역에서 도전이 가능한 미션 목록, 페이징 포함)

SELECT m.id AS mission_id,
       s.name,
       s.address,
       m.points,
       m.deadline,
       m.status
FROM mission m
JOIN store s ON m.store_id = s.id
JOIN region r ON s.region_id = r.id
WHERE r.name = '안암동'
AND m.deadline > NOW() -- 미션의 마감일이 현재 시간 이후인 것만 선택
ORDER BY m.deadline ASC -- 마감일이 빠른 순서대로 정렬
LIMIT y OFFSET (x - 1) * y ; -- 임의설정


-- 4.마이 페이지 화면 쿼리

-- 4-1.이름, 이메일, 휴대폰번호, 내 포인트 가져오기:

SELECT name, email, phone_number, points -- 테이블에 폰넘버 없..
FROM member
WHERE id = 1;

-- 4-2.작성한 리뷰 가져오기:

SELECT r.id, s.name, r.body, r.score
FROM review r
INNER JOIN store s ON r.store_id = s.id
WHERE r.member_id = 1;
