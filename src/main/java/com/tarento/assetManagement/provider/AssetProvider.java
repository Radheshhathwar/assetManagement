package com.tarento.assetManagement.provider;

import com.tarento.assetManagement.dao.model.UpdateAsset;
import com.tarento.assetManagement.dao.mapper.AssetMapper;
import com.tarento.assetManagement.dao.model.Asset;
import com.tarento.assetManagement.dao.repository.AssetRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AssetProvider {

    private static Logger logger = LogManager.getLogger(AssetProvider.class.getSimpleName());

@Autowired
    AssetMapper assetMapper;

    @Autowired
    private AssetRepository assetRepository;

    public Asset getAssetDetails(Integer id) throws Exception {
        return assetMapper.map(assetRepository.getAssetById(id));
    }

    public void saveAsset(Asset asset) throws Exception{
        assetRepository.save(asset);
    }

    public List<Asset> getLostDamagedAsset(String status){
        return (List<Asset>) assetRepository.getAssetByStatus(status);
    }


    public Asset updateAssetById(Integer id, UpdateAsset updateAsset) throws Exception {
        Asset asset = null;
        try {
            asset = assetRepository.findById(id).orElseThrow(() -> new Exception("Asset id: "+id+" not found"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        if(updateAsset.getType()!= null){
            asset.setType(updateAsset.getType());
            }else
                asset.setType(asset.getType());

        if(updateAsset.getDescription()!= null){
            asset.setDescription(updateAsset.getDescription());
        }else
            asset.setDescription(asset.getDescription());

        if(updateAsset.getBrandName()!= null){
            asset.setBrandName(updateAsset.getBrandName());
        }else
            asset.setBrandName(asset.getBrandName());

        if(updateAsset.getUniqueNumber()!= null){
            asset.setUniqueNumber(updateAsset.getUniqueNumber());
        }else
            asset.setUniqueNumber(asset.getUniqueNumber());

        if(updateAsset.getModelNumber()!= null){
            asset.setModelNumber(updateAsset.getModelNumber());
        }else
            asset.setModelNumber(asset.getModelNumber());

        if(updateAsset.getPurchaseDate()!= null){
            asset.setPurchaseDate(updateAsset.getPurchaseDate());
        }else
            asset.setPurchaseDate(asset.getPurchaseDate());

        if(updateAsset.getStatus()!= null){
            asset.setStatus(updateAsset.getStatus());
        }else
            asset.setStatus(asset.getStatus());

        if(updateAsset.isWarrantyExists()){
            asset.setWarrantyExists(updateAsset.isWarrantyExists());
        }else
            asset.setWarrantyExists(asset.getWarrantyExists());

        if(updateAsset.getWarrantyEndDate()!= null){
            asset.setWarrantyEndDate(updateAsset.getWarrantyEndDate());
        }else
            asset.setWarrantyEndDate(asset.getWarrantyEndDate());

        if(updateAsset.getOwner()!= null){
            asset.setOwner(updateAsset.getOwner());
        }else
            asset.setOwner(asset.getOwner());

        assetRepository.save(asset);
        return assetMapper.map(asset);
    }

}
