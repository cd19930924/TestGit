package com.common.model.service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator.Builder;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.Verification;
import com.emp.model.dao.impl.EmpJNDIDAOImpl;
import com.emp.model.vo.EmpVO;
import com.member.model.dao.impl.MemberDaoImpl;

	public class JWTokenUtils {
		public static DecodedJWT createToken(long memberId) {
			 /**
	         * set expired time with 1 hour later
	         */
	        LocalDateTime time = LocalDateTime.now().plusHours(1);
	        System.out.println(time);
	        /**
	         * user token builder to set token attributes
	         */
	        Builder tokenBuilder = JWT.create();
	        tokenBuilder.withExpiresAt(Date.from(time.toInstant(ZoneOffset.ofHours(8))));
	        tokenBuilder.withClaim("member_id", memberId);
	       
	        
	        /**
	         * use algorithm to set secret key for token
	         */
	        String token = tokenBuilder.sign(Algorithm.HMAC256("secretKey"));
	        
	        System.out.println(token);
	        
	        /**
	         * decode jwt getting its value
	         */
	        DecodedJWT jwt = JWT.decode(token);
	        
	        /**
	         * checking the algorithm should the same and not expired
	         */
	        Verification verification = JWT.require(Algorithm.HMAC256("secretKey"));
	        verification.build().verify(token);
	        
	        return jwt;
	        }
		public static long isTokenValid(String token) throws ClassNotFoundException {
			DecodedJWT jwt = JWT.decode(token);
			LocalDateTime time = LocalDateTime.now();
			Date date = Date.from(time.toInstant(ZoneOffset.ofHours(8)));
			jwt.getExpiresAt();
			MemberDaoImpl dao = new MemberDaoImpl();
			if(dao.isMemberIdExist(jwt.getClaim("member_id").asLong()) && date.before(jwt.getExpiresAt()) ) {
				return jwt.getClaim("member_id").asLong();
			}else {
				return 0;
			}
		}
		public static long isEmpTokenValid(String token) throws ClassNotFoundException {
			DecodedJWT jwt = JWT.decode(token);
			LocalDateTime time = LocalDateTime.now();
			Date date = Date.from(time.toInstant(ZoneOffset.ofHours(8)));
			jwt.getExpiresAt();
			EmpJNDIDAOImpl dao = new EmpJNDIDAOImpl();
			if(dao.isEmpExistById(jwt.getClaim("member_id").asLong()) && date.before(jwt.getExpiresAt()) ) {
				return jwt.getClaim("member_id").asLong();
			}else {
				return 0;
			}
		}
		public static long  getTokenId(String token) {
			DecodedJWT jwt = JWT.decode(token);
			return jwt.getClaim("member_id").asLong();
		}
	}
	    

