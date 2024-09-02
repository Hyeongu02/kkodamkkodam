package com.kkodamkkodam.mini.model;

import java.util.ArrayList;
import java.util.Map;

public interface MiniMapper {
	
	public void miniWrite(MiniDTO dto); // 미니 게시판 신설 요청 작성
	public ArrayList<MiniDTO> getMiniList(); 	 // 미니 요청 목록
	public MiniDTO voteContent(Long postNo); // 미니 신설 요청 글
	
	public void miniListView(Long postNo);  // 미니 글 조회수
	public void increaseVoteLike(MiniDTO dto); // 미니 신설 요청 글 좋아요 수
	public void addVote(VoteDTO dto);
	public void insertVoteYes(MiniDTO vote);
	public void insertVoteNo(MiniDTO vote);
	public void insertVote(VoteDTO vote);
	public MiniDTO getMiniVote(MiniDTO vote);
	public int hasUserVoted(Map<String, Object> params);
	
} 

