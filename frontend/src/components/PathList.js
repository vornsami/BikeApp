import React from 'react'
import Grid from '@material-ui/core/Grid'

import BikePath from './BikePath'
import BikePathHeader from './BikePathHeader'
import './PathList.css'

const PathList = ({paths, setSort, sortBy}) => {

  return <div className="BikePath-table">
     <Grid container spacing={3} alignItems="center">
      <Grid item xs={12} key='bp-header' className="BikePath-header">
        <BikePathHeader setSort={setSort} sortBy={sortBy}/>
      </Grid>
      {paths !== undefined && paths.map(path => {
        if (path !== undefined) {
          return <Grid item xs={12} key={path._id.$oid} className="BikePath-element">
              <BikePath path={path} />
            </Grid>
        }
      })}
    </Grid>
  </div>
}

export default PathList