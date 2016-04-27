package com.example.mobbank_client;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Button btCreate = (Button) findViewById(R.id.createButton);
		btCreate.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(MainActivity.this, CreateAccountActivity.class);
				startActivity(i);
			}
		});
		
		Button btBalance = (Button) findViewById(R.id.getBalanceButton);
		btBalance.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(MainActivity.this, GetBalanceActivity.class);
				startActivity(i);
			}
		});

		Button btDeposit = (Button) findViewById(R.id.depositButton);
		btDeposit.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(MainActivity.this, DepositActivity.class);
				startActivity(i);
			}
		});

		Button btWithdraw = (Button) findViewById(R.id.withdrawButton);
		btWithdraw.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(MainActivity.this, WithdrawActivity.class);
				startActivity(i);
			}
		});

		Button btClose = (Button) findViewById(R.id.closeButton);
		btClose.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(MainActivity.this, CloseAccountActivity.class);
				startActivity(i);
			}
		});

		Button btAccountTransactions = (Button) findViewById(R.id.acTransactionsButton);
		btAccountTransactions.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(MainActivity.this, ShowAccountTransactionsActivity.class);
				startActivity(i);
			}
		});

		Button btAllTransactions = (Button) findViewById(R.id.allTransactionsButton);
		btAllTransactions.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(MainActivity.this, ShowAllTransactionsActivity.class);
				startActivity(i);
			}
		});

		Button btAllAccounts = (Button) findViewById(R.id.allAccountsButton);
		btAllAccounts.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(MainActivity.this, ShowAllAccountsActivity.class);
				startActivity(i);
			}
		});
	}
}
