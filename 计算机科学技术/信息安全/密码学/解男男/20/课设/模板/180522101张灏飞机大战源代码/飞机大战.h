#pragma once
#include<windows.h>
#include <iostream>
using namespace std;
typedef struct Frame
{
	COORD position[2];
	int flag;
}Frame;
class Game
{
public:
	COORD position[10];//λ��
	COORD bullet[10];//�ڵ�
	Frame enemy[8];//����
	int score;
	int rank;
	int rankf;
	string title;
	int flag_rank;

	Game();

	//��ʼ�����С��Լ��ɻ����ڵ������ˣ��ϰ��
	void initPlane();
	void initBullet();
	void initEnemy();
	void planeMove(char);
	void bulletMove();
	void enemyMove();

	//�������
	void drawPlane();
	void drawPlaneToNull();
	void drawBullet();
	void drawBulletToNull();
	void drawEnemy();
	void drawEnemyToNull();

	//�������һ��
	void drawThisBulletToNull(COORD);
	void drawThisEnemyToNull(Frame);

	void Pause();
	void Playing();
	void judgePlane();
	void judgeEnemy();
	void Shoot();
	void GameOver();
	void printScore();
};