import React from 'react'
import Grid from '@material-ui/core/Grid'

import BikePath from './BikePath'
import BikePathHeader from './BikePathHeader'
import PageSelector from './PageSelector'
import './PathList.css'

const PathList = ({paths, setSort, sortBy, page, setPage}) => {

  return <div className="BikePath-table">
     <Grid container spacing={2} alignItems="center">
      <Grid item xs={12} key='bp-header' className="BikePath-header">
        <BikePathHeader setSort={setSort} sortBy={sortBy} setPage={setPage}/>
      </Grid>
      {paths !== undefined && paths.map(path => {
        if (path !== undefined) {
          return <Grid item xs={12} key={path._id.$oid} className="BikePath-element">
              <BikePath path={path} />
            </Grid>
        }
      })}
      <Grid item xs={12} key='bp-pages' className="BikePath-header">
        <PageSelector page={page} setPage={setPage}/>
      </Grid>
    </Grid>
  </div>
}

export default PathList