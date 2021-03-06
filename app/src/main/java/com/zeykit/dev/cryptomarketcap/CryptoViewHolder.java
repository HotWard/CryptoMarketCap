package com.zeykit.dev.cryptomarketcap;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputFilter;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class CryptoViewHolder extends RecyclerView.ViewHolder {

    private TextView mRankTextView;
    private ImageView mIconImageView;
    private TextView mNameTextView;
    private TextView mPriceTextView;
    private TextView mPercentChangeTextView;

    Context context;

    public CryptoViewHolder(View v) {
        super(v);

        context = v.getContext();

        mRankTextView = (TextView) v.findViewById(R.id.rankTextView);
        mIconImageView = (ImageView) v.findViewById(R.id.iconImageView);
        mNameTextView = (TextView) v.findViewById(R.id.nameTextView);
        mPriceTextView = (TextView) v.findViewById(R.id.priceTextView);
        mPercentChangeTextView = (TextView) v.findViewById(R.id.percentChangeTextView);

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MoreAboutCryptoDialog.selectedCrypto = mNameTextView.getText().toString();

                String runningActivity = MainActivity.mRunningActivity;
                if (runningActivity.contains("MainActivity")) {
                    MainActivity.currentView = "MainActivity";
                } else {
                    MainActivity.currentView = "PinnedCoinsActivity";
                }

                if (!MoreAboutCryptoDialog.selectedCrypto.contains("No pinned coins")) {
                    Intent intent = new Intent(v.getContext(),
                            MoreAboutCryptoDialog.class);
                    v.getContext().startActivity(intent);
                }
            }
        });
    }

    private String getDefaultCurrency() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getString("currency_list_preference", "USD");
    }

    public void bind(CryptoAdapter cryptoAdapter) {

        int maxLength;
        if (!getDefaultCurrency().equals("BTC")) {
            maxLength = 9;
        } else {
            maxLength = 11;
        }
        InputFilter[] fArray = new InputFilter[1];
        fArray[0] = new InputFilter.LengthFilter(maxLength);
        mPriceTextView.setFilters(fArray);

        mRankTextView.setText(cryptoAdapter.getRank());
        mIconImageView.setImageDrawable(cryptoAdapter.getIcon());
        mNameTextView.setText(cryptoAdapter.getName());
        mPriceTextView.setText(cryptoAdapter.getPrice());
        mPercentChangeTextView.setText(cryptoAdapter.getPercentChange());

        String mPercentChange = cryptoAdapter.getPercentChange();
        if (!isNoPanicModeEnable()) {
            if (mPercentChange.contains("-")) {
                mPercentChangeTextView.setTextColor(Color.parseColor("#ff3333"));
            } else if (mPercentChange.equals("0.0%")) {
                mPercentChangeTextView.setTextColor(Color.parseColor("#ffffff"));
            } else if (!mPercentChange.isEmpty()) {
                String percentChange = "+" + cryptoAdapter.getPercentChange();
                mPercentChangeTextView.setText(percentChange);
                mPercentChangeTextView.setTextColor(Color.parseColor("#00ff55"));
            }
        } else {
            if (!mPercentChange.contains("-") && !mPercentChange.equals("0.0%")) {
                String percentChange = "+" + cryptoAdapter.getPercentChange();
                mPercentChangeTextView.setText(percentChange);
            }
            mPercentChangeTextView.setTextColor(Color.parseColor("#ffffff"));
        }

        String mCrypto = cryptoAdapter.getName();
        if (mCrypto.contains("(BTC)")) {
            setIcon(R.drawable.bitcoin_icon);
        } else if (mCrypto.contains("(ETH)")) {
            setIcon(R.drawable.eth_icon);
        } else if (mCrypto.contains("(XRP)")) {
            setIcon(R.drawable.ripple_icon);
        } else if (mCrypto.contains("(LTC)")) {
            setIcon(R.drawable.litecoin_icon);
        } else if (mCrypto.contains("(ETC)")) {
            setIcon(R.drawable.etc_icon);
        } else if (mCrypto.contains("(XEM)")) {
            setIcon(R.drawable.nem_icon);
        } else if (mCrypto.contains("(DASH)")) {
            setIcon(R.drawable.dash_icon);
        } else if (mCrypto.contains("(MIOTA)")) {
            setIcon(R.drawable.iota_icon);
        } else if (mCrypto.contains("(BTS)")) {
            setIcon(R.drawable.bitshares_icon);
        } else if (mCrypto.contains("(XMR)")) {
            setIcon(R.drawable.monero_icon);
        } else if (mCrypto.contains("(STRAT)")) {
            setIcon(R.drawable.stratis_icon);
        } else if (mCrypto.contains("(ZEC)")) {
            setIcon(R.drawable.zcash_icon);
        } else if (mCrypto.contains("(SC)")) {
            setIcon(R.drawable.siacoin_icon);
        } else if (mCrypto.contains("(WAVES)")) {
            setIcon(R.drawable.waves_icon);
        } else if (mCrypto.contains("(GNT)")) {
            setIcon(R.drawable.golem_icon);
        } else if (mCrypto.contains("(ICN)")) {
            setIcon(R.drawable.iconomi_icon);
        } else if (mCrypto.contains("(BCN)")) {
            setIcon(R.drawable.bytecoin_icon);
        } else if (mCrypto.contains("(ANS)")) {
            setIcon(R.drawable.antshares_icon);
        } else if (mCrypto.contains("(STEEM)")) {
            setIcon(R.drawable.steem_icon);
        } else if (mCrypto.contains("(XLM)")) {
            setIcon(R.drawable.stellar_icon);
        } else if (mCrypto.contains("(BCC)")) {
            setIcon(R.drawable.bitconnect_icon);
        } else if (mCrypto.contains("(DOGE)")) {
            setIcon(R.drawable.dogecoin_icon);
        } else if (mCrypto.contains("(LSK)")) {
            setIcon(R.drawable.lisk_icon);
        } else if (mCrypto.contains("(REP)")) {
            setIcon(R.drawable.augur_icon);
        } else if (mCrypto.contains("(GAME)")) {
            setIcon(R.drawable.gamecredits_icon);
        } else if (mCrypto.contains("(ARDR)")) {
            setIcon(R.drawable.ardor_icon);
        } else if (mCrypto.contains("(FCT)")) {
            setIcon(R.drawable.factom_icon);
        } else if (mCrypto.contains("(DGB)")) {
            setIcon(R.drawable.digibyte_icon);
        } else if (mCrypto.contains("(GNO)")) {
            setIcon(R.drawable.gnosis_icon);
        } else if (mCrypto.contains("(MAID)")) {
            setIcon(R.drawable.maidsafecoin_icon);
        } else if (mCrypto.contains("(DCR)")) {
            setIcon(R.drawable.decred_icon);
        } else if (mCrypto.contains("(VERI)")) {
            setIcon(R.drawable.veritaseum_icon);
        } else if (mCrypto.contains("(KMD)")) {
            setIcon(R.drawable.komodo_icon);
        } else if (mCrypto.contains("(BAT)")) {
            setIcon(R.drawable.basicattentiontoken_icon);
        } else if (mCrypto.contains("(GBYTE)")) {
            setIcon(R.drawable.byteball_icon);
        } else if (mCrypto.contains("(DGD)")) {
            setIcon(R.drawable.digixdao_icon);
        } else if (mCrypto.contains("(1ST)")) {
            setIcon(R.drawable.firstblood_icon);
        } else if (mCrypto.contains("(NXT)")) {
            setIcon(R.drawable.nxt_icon);
        } else if (mCrypto.contains("(USDT)")) {
            setIcon(R.drawable.tether_icon);
        } else if (mCrypto.contains("(MGO)")) {
            setIcon(R.drawable.mobilego_icon);
        } else if (mCrypto.contains("(SNGLS)")) {
            setIcon(R.drawable.singulardtv_icon);
        } else if (mCrypto.contains("(SYS)")) {
            setIcon(R.drawable.syscoin_icon);
        } else if (mCrypto.contains("(BTCD)")) {
            setIcon(R.drawable.bitcoindark_icon);
        } else if (mCrypto.contains("(ANT)")) {
            setIcon(R.drawable.aragon_icon);
        } else if (mCrypto.contains("(PIVX)")) {
            setIcon(R.drawable.pivx_icon);
        } else if (mCrypto.contains("(ROUND)")) {
            setIcon(R.drawable.round_icon);
        } else if (mCrypto.contains("(EMC)")) {
            setIcon(R.drawable.emercoin_icon);
        } else if (mCrypto.contains("(UBQ)")) {
            setIcon(R.drawable.ubiq_icon);
        } else if (mCrypto.contains("(LKK)")) {
            setIcon(R.drawable.lykke_icon);
        } else if (mCrypto.contains("(LBC)")) {
            setIcon(R.drawable.lbry_icon);
        } else if (mCrypto.contains("(MCAP)")) {
            setIcon(R.drawable.mcap_icon);
        } else if (mCrypto.contains("(ARK)")) {
            setIcon(R.drawable.ark_icon);
        } else if (mCrypto.contains("(PPY)")) {
            setIcon(R.drawable.peerplays_icon);
        } else if (mCrypto.contains("(XAS)")) {
            setIcon(R.drawable.asch_icon);
        } else if (mCrypto.contains("(PPC)")) {
            setIcon(R.drawable.peercoin_icon);
        } else if (mCrypto.contains("(RLC)")) {
            setIcon(R.drawable.iexec_icon);
        } else if (mCrypto.contains("(QRL)")) {
            setIcon(R.drawable.quantum_icon);
        } else if (mCrypto.contains("(AMP)")) {
            setIcon(R.drawable.synereo_icon);
        } else if (mCrypto.contains("(SJCX)")) {
            setIcon(R.drawable.storjcoin_icon);
        } else if (mCrypto.contains("(NMR)")) {
            setIcon(R.drawable.numeraire_icon);
        } else if (mCrypto.contains("(MLN)")) {
            setIcon(R.drawable.melon_icon);
        } else if (mCrypto.contains("(RDD)")) {
            setIcon(R.drawable.reddcoin_icon);
        } else if (mCrypto.contains("(WINGS)")) {
            setIcon(R.drawable.wings_icon);
        } else if (mCrypto.contains("(NMC)")) {
            setIcon(R.drawable.namecoin_icon);
        } else if (mCrypto.contains("(BAY)")) {
            setIcon(R.drawable.bitbay_icon);
        } else if (mCrypto.contains("(XCP)")) {
            setIcon(R.drawable.counterparty_icon);
        } else if (mCrypto.contains("(NXS)")) {
            setIcon(R.drawable.nexus_icon);
        } else if (mCrypto.contains("(LEO)")) {
            setIcon(R.drawable.leocoin_icon);
        } else if (mCrypto.contains("(MYST)")) {
            setIcon(R.drawable.mysterium_icon);
        } else if (mCrypto.contains("(XVG)")) {
            setIcon(R.drawable.verge_icon);
        } else if (mCrypto.contains("(CLOAK)")) {
            setIcon(R.drawable.cloakcoin_icon);
        } else if (mCrypto.contains("(OMNI)")) {
            setIcon(R.drawable.omni_icon);
        } else if (mCrypto.contains("(XEL)")) {
            setIcon(R.drawable.elastic_icon);
        } else if (mCrypto.contains("(BLK)")) {
            setIcon(R.drawable.blackcoin_icon);
        } else if (mCrypto.contains("(EDG)")) {
            setIcon(R.drawable.edgeless_icon);
        } else if (mCrypto.contains("(NLG)")) {
            setIcon(R.drawable.gulden_icon);
        } else if (mCrypto.contains("(XAUR)")) {
            setIcon(R.drawable.xaurum_icon);
        } else if (mCrypto.contains("(MONA)")) {
            setIcon(R.drawable.monacoin_icon);
        } else if (mCrypto.contains("(XZC)")) {
            setIcon(R.drawable.zcoin_icon);
        } else if (mCrypto.contains("(VTC)")) {
            setIcon(R.drawable.vertcoin_icon);
        } else if (mCrypto.contains("(EOS)")) {
            setIcon(R.drawable.eos_icon);
        } else if (mCrypto.contains("(SNT)")) {
            setIcon(R.drawable.status_icon);
        } else if (mCrypto.contains("(BNT)")) {
            setIcon(R.drawable.bancor_icon);
        } else if (mCrypto.contains("(PAY)")) {
            setIcon(R.drawable.tenx_icon);
        } else if (mCrypto.contains("(DCT)")) {
            setIcon(R.drawable.decent_icon);
        } else if (mCrypto.contains("(MTL)")) {
            setIcon(R.drawable.metal_icon);
        } else if (mCrypto.contains("(FUN)")) {
            setIcon(R.drawable.funfair_icon);
        } else if (mCrypto.contains("(SOAR)")) {
            setIcon(R.drawable.soarcoin_icon);
        } else if (mCrypto.contains("(DICE)")) {
            setIcon(R.drawable.etheroll_icon);
        } else if (mCrypto.contains("(DBIX)")) {
            setIcon(R.drawable.dubaicoin_icon);
        } else if (mCrypto.contains("(EDR)")) {
            setIcon(R.drawable.edinar_icon);
        } else if (mCrypto.contains("(YBC)")) {
            setIcon(R.drawable.ybcoin_icon);
        } else if (mCrypto.contains("(SKY)")) {
            setIcon(R.drawable.skycoin_icon);
        } else if (mCrypto.contains("(QAU)")) {
            setIcon(R.drawable.quantum_icon);
        } else if (mCrypto.contains("(HMQ)")) {
            setIcon(R.drawable.humaniq_icon);
        } else if (mCrypto.contains("(STORJ)")) {
            setIcon(R.drawable.storj_icon);
        } else if (mCrypto.contains("(ADT)")) {
            setIcon(R.drawable.adtoken_icon);
        } else if (mCrypto.contains("(POT)")) {
            setIcon(R.drawable.potcoin_icon);
        } else if (mCrypto.contains("(BLOCK)")) {
            setIcon(R.drawable.blocknet_icon);
        } else if (mCrypto.contains("(VSL)")) {
            setIcon(R.drawable.vslice_icon);
        } else if (mCrypto.contains("(OBITS)")) {
            setIcon(R.drawable.obits_icon);
        } else if (mCrypto.contains("(SIB)")) {
            setIcon(R.drawable.sibcoin_icon);
        } else if (mCrypto.contains("(SLS)")) {
            setIcon(R.drawable.salus_icon);
        } else if (mCrypto.contains("(SWT)")) {
            setIcon(R.drawable.swarmcity_icon);
        } else if (mCrypto.contains("(UNY)")) {
            setIcon(R.drawable.unityingot_icon);
        } else if (mCrypto.contains("(IOC)")) {
            setIcon(R.drawable.iocoin_icon);
        } else if (mCrypto.contains("(BURST)")) {
            setIcon(R.drawable.burst_icon);
        } else if (mCrypto.contains("(NVC)")) {
            setIcon(R.drawable.novacoin_icon);
        } else if (mCrypto.contains("(TKN)")) {
            setIcon(R.drawable.tokencard_icon);
        } else if (mCrypto.contains("(VIA)")) {
            setIcon(R.drawable.viacoin_icon);
        } else if (mCrypto.contains("(QTUM)")) {
            setIcon(R.drawable.qtum_icon);
        } else if (mCrypto.contains("(CVC)")) {
            setIcon(R.drawable.civic_icon);
        } else if (mCrypto.contains("(BDL)")) {
            setIcon(R.drawable.bitdeal_icon);
        } else if (mCrypto.contains("(PPT)")) {
            setIcon(R.drawable.populous_icon);
        } else if (mCrypto.contains("(OMG)")) {
            setIcon(R.drawable.omisego_icon);
        } else if (mCrypto.contains("(PART)")) {
            setIcon(R.drawable.particl_icon);
        } else if (mCrypto.contains("(PLR)")) {
            setIcon(R.drawable.pillar_icon);
        } else if (mCrypto.contains("(XRL)")) {
            setIcon(R.drawable.rialto_icon);
        } else if (mCrypto.contains("(ION)")) {
            setIcon(R.drawable.ion_icon);
        } else if (mCrypto.contains("(GOLOS)")) {
            setIcon(R.drawable.golos_icon);
        } else if (mCrypto.contains("(TAAS)")) {
            setIcon(R.drawable.taas_icon);
        } else {
            mIconImageView.setImageDrawable(null);
        }
    }

    /**
     * Change crypto's icon
     * @param drawable icon
     */
    private void setIcon(int drawable) {
        mIconImageView.setImageResource(drawable);
    }

    private boolean isNoPanicModeEnable() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getBoolean("no_panic_switch", false);
    }
}
