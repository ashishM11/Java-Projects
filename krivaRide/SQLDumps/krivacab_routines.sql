-- MySQL dump 10.13  Distrib 5.7.17, for macos10.12 (x86_64)
--
-- Host: localhost    Database: krivacab
-- ------------------------------------------------------
-- Server version	5.7.21

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Dumping routines for database 'krivacab'
--
/*!50003 DROP PROCEDURE IF EXISTS `sp_CreateUsers` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_CreateUsers`(
    IN p_userFname VARCHAR(15),
	IN p_userLname VARCHAR(15),
    IN p_userMailID VARCHAR(50),
    IN p_userMobileNo VARCHAR(10),
    IN p_userType VARCHAR(15),
	OUT p_userCreationOP INT
)
BEGIN
	DECLARE cntMailId,cntMobile tinyINT;
     
   SELECT 
    SUM(userMailID = p_userMailID),
    SUM(userMobileNo = p_userMobileNo)
    into cntMailId,cntMobile
	FROM tblUser ;
    
    IF cntMailId!=0 THEN
		SET p_userCreationOP=-99;
    ELSEIF cntMobile!=0 THEN
		SET p_userCreationOP=-999;
    ELSE
		INSERT INTO tbluser (userFname,userLname,userMailID,userMobileNo,userType)
		VALUES (p_userFname,p_userLname,p_userMailID,p_userMobileNo,p_userType);
        
		SELECT userId into p_userCreationOP 
		from tbluser order by 1 desc limit 1;
    END IF;
    
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sp_ForgetPassword` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_ForgetPassword`(
	IN p_UserEmail VARCHAR(50),
    OUT p_UserId INT,
    OUT p_EncryptedPwd VARCHAR(300)
)
BEGIN

    SELECT userId INTO p_UserId
    FROM tblUser
    WHERE (userMailID=p_UserEmail);
    
	IF p_UserId > 0 THEN
		select pwdChar into p_EncryptedPwd 
        from tblpassword where usrId=p_UserId;
    ELSE
		SET p_UserId = 0;
        SET p_EncryptedPwd='No Entry Found In Database.';
    END IF;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sp_getTableRowCount` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_getTableRowCount`(
	IN p_tableName VARCHAR(25),
    OUT p_tabRowCount INT
)
BEGIN

	DECLARE isTableAvailable int;
	SET p_tabRowCount = 0;
	select count(*) into isTableAvailable 
    from information_schema.TABLES
    Where TABLE_SCHEMA='krivacab'
    and TABLE_NAME=p_tableName;
    
    IF isTableAvailable > 0 && !isnull(p_tableName) THEN
		set @txt= concat("select count(*) into ",p_tabRowCount," from ",p_tableName);
		prepare exec from @txt;
		execute exec;
    ELSE
		SET p_tabRowCount = 0;
    END IF;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sp_resetNewPassword` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_resetNewPassword`(
	IN p_newPwd VARCHAR(15),
    IN p_oldPwdChar varchar(256),
    IN p_userId int,
    OUT p_resultOp int
)
BEGIN	
	
	DECLARE newPwdChar VARCHAR(128);
	DECLARE resOp int;
        
    Select count(*) into resOp 
    from tblpassword
    Where userId=p_userId and pwdChar=p_oldPwdChar;
    
    IF resOp > 0 Then
		Set p_resultOp = 1;
        SET newPwdChar = md5(p_newPwd);
		
        UPDATE tblpassword 
        SET usrPwd=p_newPwd WHERE userId=p_userId;
        
        UPDATE tblpassword
        SET pwdChar=newPwdChar WHERE userId=p_userId;
		
	ELSE 
		SET p_resultOp = 0;
	END IF;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sp_UpdatePassword` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_UpdatePassword`(
	IN p_UsrID INT,
    IN p_OldPwd VARCHAR(300),
    IN p_NewPwd VARCHAR(300),
    OUT opRes INT
)
BEGIN

	SELECT COUNT(*) into opRes
    FROM tblpassword
    WHERE usrId=p_UsrID and pwdChar=p_OldPwd;
    
    IF opRes !=0 THEN
		UPDATE tblpassword
        SET pwdChar=p_NewPwd
        WHERE usrId=p_UsrID and pwdChar=p_OldPwd;
    ELSE
		SET opRes=0;
    END IF;

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sp_updateUsersProfile` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_updateUsersProfile`(
	IN p_userId INT,
    IN new_userMobile VARCHAR(10),
    IN new_userEmail VARCHAR(40),
    OUT p_OutRes int
)
BEGIN

	declare checkNewMail INT;
    declare checkNewMobile INT;
    
    select count(*) into checkNewMail from tbluser
    where userMailID=new_userEmail and userId!=p_userId;
    
    select count(*) into checkNewMobile from tbluser
    where userMobileNo=new_userMobile and userId!=p_userId;
    
    IF checkNewMail > 0 THEN
		SET p_OutRes=-99;
    ELSEIF checkNewMobile > 0 THEN
		SET p_OutRes=-999;
    ELSE
		UPDATE tbluser 
        set userMobileNo=new_userMobile,userMailID=new_userEmail
        where userId=p_userId;
        
        SET p_OutRes=1;
        
	END IF;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sp_UserLogin` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_UserLogin`(
	IN userIdenInput varchar(50),
    IN userPassword varchar(50),
    OUT userId INT
)
BEGIN
	
        DECLARE md5pwd VARCHAR(128);
        
        IF  LENGTH(userPassword) > 4 THEN
			SET md5pwd = MD5(userPassword);
		END IF;

		IF LENGTH(md5pwd) > 5 and LENGTH(userIdenInput) > 5 THEN
			select users.userId into userId
			from tbluser users 
			inner join tblpassword userspwd on users.userId=userspwd.userId
			where (users.userMailID=userIdenInput or users.userMobileNo=userIdenInput)
			and userspwd.pwdChar=md5pwd;
		END IF;
        
        IF isnull(userId) Then
			SET userId=0;
        END IF;

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-04-24 22:25:24
